package com.worldline.t21kotlinbaseproject.data.utils

import com.worldline.t21kotlinbaseproject.BuildConfig
import com.worldline.t21kotlinbaseproject.domain.model.ServerErrorException
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiClient<ApiService>(
    private val apiServiceClass: Class<ApiService>,
    private val baseUrl: String
) {

    fun createApiService(): ApiService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor()
        )
        return makeService(
            okHttpClient,
            Gson()
        )
    }

    fun <T> executeRequest(call: Call<T>): T? {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> response.body()
                false -> throw ServerErrorException()
            }
        } catch (exception: Throwable) {
            throw ServerErrorException()
        }
    }

    private fun makeService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(apiServiceClass)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}