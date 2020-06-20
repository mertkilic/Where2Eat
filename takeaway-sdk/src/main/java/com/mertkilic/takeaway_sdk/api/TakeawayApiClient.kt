package com.mertkilic.takeaway_sdk.api


import com.mertkilic.takeaway_sdk.model.RestaurantListResponse
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TakeawayApiClient(
  private val httpClient: OkHttpClient,
  private val baseUrl: String
  ) : ApiClient {

  private val takeawayApi: TakeawayApi = createApi(httpClient)

  override fun getType() = ApiType.TAKEAWAY

  private fun createApi(httpClient: OkHttpClient): TakeawayApi {
    val retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(httpClient)
      .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
      .build()

    return retrofit.create(TakeawayApi::class.java)
  }

  override suspend fun getRestaurants(): RestaurantListResponse {
    TODO("Not yet implemented")
  }
}