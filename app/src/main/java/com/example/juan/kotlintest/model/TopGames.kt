package com.example.juan.kotlintest.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Juan on 12/11/2017.
 */
class TopGames(@SerializedName("top") val top: kotlin.collections.List<TopGame> = java.util.ArrayList())