package com.example.juan.kotlintest.service

import com.example.juan.kotlintest.model.TopGames
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Juan on 12/11/2017.
 */

interface TwitchService {

    @GET("games/top?limit=100")
    fun getTop(@Query("client_id") clientId: String) : Call<TopGames>
}