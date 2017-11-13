package com.example.juan.kotlintest.interfaces

import com.example.juan.kotlintest.Settings.URL
import com.example.juan.kotlintest.service.TwitchService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Juan on 12/11/2017.
 */
class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun twitchService() : TwitchService {
        return retrofit.create(TwitchService::class.java)
    }
}