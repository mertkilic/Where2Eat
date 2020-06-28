package com.mertkilic.where2eat.features.restaurantlist.data

import com.mertkilic.takeaway_sdk.api.ApiClient
import com.mertkilic.takeaway_sdk.model.RestaurantListResponse
import com.mertkilic.takeaway_sdk.model.RestaurantResponse
import com.mertkilic.where2eat.data.ErrorHandler
import com.mertkilic.where2eat.data.Result
import com.mertkilic.where2eat.utils.CoroutineTestRule
import com.nhaarman.mockito_kotlin.MockitoKotlinException
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class RestaurantNetworkDataSourceTest {

  @get:Rule
  var coroutineTestRule = CoroutineTestRule()

  private val apiClient = Mockito.mock(ApiClient::class.java)
  private lateinit var networkDataSource: RestaurantNetworkDataSource

  @Before
  fun setUp() {
    networkDataSource = RestaurantNetworkDataSource(apiClient)
  }

  @Test
  fun `get restaurants from api client with success`() {
    val sdkResponse = RestaurantListResponse(listOf(RestaurantResponse("Fes", null, null)))
    val expectedResult = Result.success(RestaurantsPage(sdkResponse))

    runBlockingTest {
      given(apiClient.getRestaurants()).willReturn(sdkResponse)

      val actualResult = networkDataSource.fetchRestaurantList()

      assertEquals(expectedResult, actualResult)
    }
  }

  @Test
  fun `get restaurants from api client with an error`() {
    val exception = MockitoKotlinException("", Throwable())
    val expectedResult = Result.error(ErrorHandler.handleError(exception), null)

    runBlockingTest {
      given(apiClient.getRestaurants()).willThrow(exception)

      val actualResult = networkDataSource.fetchRestaurantList()

      assertEquals(expectedResult, actualResult)
    }
  }
}