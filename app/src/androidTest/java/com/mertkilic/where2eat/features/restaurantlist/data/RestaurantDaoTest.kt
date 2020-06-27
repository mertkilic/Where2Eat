package com.mertkilic.where2eat.features.restaurantlist.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mertkilic.where2eat.database.TakeawayDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RestaurantDaoTest {
  private lateinit var dao: RestaurantDao
  private lateinit var db: TakeawayDatabase

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun createDb() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room.inMemoryDatabaseBuilder(
      context, TakeawayDatabase::class.java).build()
    dao = db.restaurantDao()
  }

  @After
  @Throws(IOException::class)
  fun closeDb() {
    db.close()
  }

  @Test
  @Throws(Exception::class)
  fun addToFavoritesAndRead() {
    val favorite = Favorite("Tanoi Sushi")

    runBlocking {
      dao.addToFavorites(favorite)

      val favorites = dao.getFavorites()
      assertThat(favorites[0], equalTo(favorite))
    }
  }

  @Test
  @Throws(Exception::class)
  fun addToFavoritesAndRemove() {
    val favorite = Favorite("Tanoi Sushi")

    runBlocking {
      dao.addToFavorites(favorite)

      val favorites = dao.getFavorites()
      assertThat(favorites[0], equalTo(favorite))

      dao.removeFromFavorites(favorite)

      val expectedEmptyList = dao.getFavorites()
      assertTrue(expectedEmptyList.isEmpty())
    }
  }

/*  @ExperimentalCoroutinesApi
  @Ignore
  @Test
  @Throws(Exception::class)
  fun getRestaurantsSorted() {
    runBlockingTest {
      dao.insertOrUpdate(restaurantsMixed())
    }
    val querySortedByDistance = SimpleSQLiteQuery("SELECT * FROM restaurants ORDER BY isFavorite DESC, status ASC, distance ASC")

    TestCoroutineScope().launch(Dispatchers.Main){
      val restaurantsSorted = dao.getBySortingValue(querySortedByDistance)//.getOrAwaitValue()
      Log.d("","")
    }
  }*/
}