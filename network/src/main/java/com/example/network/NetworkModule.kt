package com.example.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    private var additionalInterceptors: List<Interceptor> = emptyList()

    fun addInterceptor(interceptor: Interceptor) {
        additionalInterceptors = additionalInterceptors + interceptor
    }

    private val okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        additionalInterceptors.forEach { builder.addInterceptor(it) }

        builder.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BookApi by lazy {
        retrofit.create(BookApi::class.java)
    }
}