package com.mertkilic.where2eat.features.restaurantlist.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mertkilic.where2eat.R
import com.mertkilic.where2eat.data.SharedPrefStorage
import com.mertkilic.where2eat.data.Storage
import com.mertkilic.where2eat.di.ApplicationComponent
import com.mertkilic.where2eat.di.Dagger
import com.mertkilic.where2eat.utils.CoroutineTestRule
import com.mertkilic.where2eat.utils.ModelTestFactory.restaurants
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


@ExperimentalCoroutinesApi
class RestaurantLocalDataSourceTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  var coroutineTestRule = CoroutineTestRule()

  private val dao = Mockito.mock(RestaurantDao::class.java)
  private val sharedPrefStorage = Mockito.mock(SharedPrefStorage::class.java)
  private val appComponent = Mockito.mock(ApplicationComponent::class.java)
  private val context = Mockito.mock(Context::class.java)

  private lateinit var restaurantLocalDataSource: RestaurantLocalDataSource

  @Before
  fun setUp() {
    restaurantLocalDataSource = RestaurantLocalDataSource(dao, sharedPrefStorage)

    given(
      sharedPrefStorage.getString(
        Storage.Key.SORT_VALUE,
        "Popularity"
      )
    ).willReturn("Popularity")

    Dagger.applicationComponent = appComponent
    given(Dagger.applicationComponent.context()).willReturn(context)
    given(context.getString(R.string.popularity)).willReturn("Popularity")
  }

  @Test
  fun `get all restaurants sorted by given value`() {
    val captor= argumentCaptor<SimpleSQLiteQuery>()
    val expectedQuery =
      "SELECT * FROM restaurants ORDER BY isFavorite DESC, status ASC, popularity DESC"

    restaurantLocalDataSource.getRestaurantsSortedBy()

    verify(dao).getBySortingValue(captor.capture())

    Assert.assertEquals(expectedQuery, captor.firstValue.sql)
  }

  @Test
  fun `search restaurants by query`() {
    val userInput = "sushi"
    val captor= argumentCaptor<SimpleSQLiteQuery>()
    val expectedQuery =
      "SELECT * FROM restaurants WHERE name LIKE '%$userInput%' ORDER BY isFavorite DESC, status ASC, popularity DESC"

    restaurantLocalDataSource.getRestaurantsSortedBy("sushi")

    verify(dao).getBySortingValue(captor.capture())

    Assert.assertEquals(expectedQuery, captor.firstValue.sql)
  }

  @Test
  fun saveRestaurants() {
    val favorites = listOf(Favorite("Tommis Burger"))
    val restaurantsToBeSaved = restaurants().map {
      if (favorites.contains(Favorite(it.name)))
        it.isFavorite = true
      it
    }

    given(dao.getFavorites()).willReturn(favorites)

    runBlockingTest {
      restaurantLocalDataSource.saveRestaurants(restaurants())

      verify(dao).insertOrUpdate(restaurantsToBeSaved)
    }
  }

}