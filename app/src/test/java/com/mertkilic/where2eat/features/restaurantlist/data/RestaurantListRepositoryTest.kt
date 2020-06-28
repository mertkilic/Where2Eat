package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.mertkilic.where2eat.data.Result
import com.mertkilic.where2eat.utils.CoroutineTestRule
import com.mertkilic.where2eat.utils.ModelTestFactory.restaurants
import com.mertkilic.where2eat.utils.getOrAwaitValue
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class RestaurantListRepositoryTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  var coroutineTestRule = CoroutineTestRule()

  private val localDataSource = Mockito.mock(RestaurantLocalDataSource::class.java)
  private val remoteDataSource = Mockito.mock(RestaurantNetworkDataSource::class.java)

  private lateinit var repository: RestaurantListRepository

  @Before
  fun setUp() {
    repository = RestaurantListRepository(localDataSource, remoteDataSource)
  }

  @Test
  fun addToFavorites() {
    val expectedResult = Result.success(Unit)
    runBlockingTest {
      given(localDataSource.addToFavorites(Favorite("Sushi Place"))).willReturn(expectedResult)

      val actualResult = repository.addToFavorites("Sushi Place")

      assertEquals(expectedResult, actualResult)
    }
  }

  @Test
  fun searchRestaurants() {
    val restaurantsLiveData = MutableLiveData(restaurants())
    val expectedResult = Result.success(restaurants())

    given(localDataSource.getRestaurantsSortedBy("sushi")).willReturn(restaurantsLiveData)

    val actualResult = repository.searchRestaurants("sushi").getOrAwaitValue()

    assertEquals(expectedResult, actualResult)
  }

  @Test
  fun getSelectedSortType() {
    given(localDataSource.sortType).willReturn("Popularity")

    val selectedSortType = repository.getSelectedSortType()

    assertEquals(selectedSortType, "Popularity")
  }

  @Test
  fun observeRestaurantsFromNetwork() {
    val expectedResult = Result.success(RestaurantsPage(restaurants()))

    runBlockingTest {
      given(remoteDataSource.fetchRestaurantList()).willReturn(expectedResult)

      val actualResult = repository.observeRestaurantsFromNetwork()

      assertEquals(expectedResult, actualResult)
    }
  }

}