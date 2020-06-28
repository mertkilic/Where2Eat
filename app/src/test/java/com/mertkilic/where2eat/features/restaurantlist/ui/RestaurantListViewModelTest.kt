package com.mertkilic.where2eat.features.restaurantlist.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.mertkilic.where2eat.data.ErrorHandler
import com.mertkilic.where2eat.data.Result
import com.mertkilic.where2eat.features.restaurantlist.data.RestaurantListRepository
import com.mertkilic.where2eat.utils.CoroutineTestRule
import com.mertkilic.where2eat.utils.ModelTestFactory.restaurants
import com.mertkilic.where2eat.utils.getOrAwaitValue
import com.nhaarman.mockito_kotlin.MockitoKotlinException
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class RestaurantListViewModelTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  var coroutineTestRule = CoroutineTestRule()

  private val repository = Mockito.mock(RestaurantListRepository::class.java)

  private lateinit var viewModel: RestaurantListViewModel

  @Before
  fun setUp() {
    viewModel = RestaurantListViewModel(repository, TestCoroutineScope())
  }

  @Test
  fun `restaurant search success`() {
    val loadingState = Result.loading(null)
    val successResult = Result.success(restaurants())

    given(repository.searchRestaurants("sushi")).willReturn(MutableLiveData(successResult))

    val liveDataResult = viewModel.searchRestaurants("sushi")

    assertEquals(loadingState, liveDataResult.getOrAwaitValue())
    assertEquals(successResult, liveDataResult.getOrAwaitValue())
  }

  @Test
  fun `restaurant search error`() {
    val exception = MockitoKotlinException("", Throwable())
    val loadingState = Result.loading(null)
    val errorResult = Result.error(ErrorHandler.handleError(exception), null)

    given(repository.searchRestaurants("sushi")).willReturn(MutableLiveData(errorResult))

    val liveDataResult = viewModel.searchRestaurants("sushi")

    assertEquals(loadingState, liveDataResult.getOrAwaitValue())
    assertEquals(errorResult, liveDataResult.getOrAwaitValue())
  }

  @Test
  fun `on search view cleared`() {
    val loadingState = Result.loading(null)
    val successResult = Result.success(restaurants())

    given(repository.observeRestaurantsFromDb()).willReturn(MutableLiveData(restaurants()))

    val liveDataResult = viewModel.resetView()

    assertEquals(loadingState, liveDataResult.getOrAwaitValue())
    assertEquals(successResult, liveDataResult.getOrAwaitValue())
  }
}