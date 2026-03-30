package com.example.network

import okhttp3.logging.HttpLoggingInterceptor

object NetworkDebugInitializer {

    fun init() {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        NetworkModule.addInterceptor(loggingInterceptor)
    }
}