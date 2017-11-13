package com.example.juan.kotlintest.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Juan on 12/11/2017.
 */

class Game (@SerializedName("_id") var id: String, var name:String, var popularity: String, @SerializedName("localized_name") var localizedName:String, var box : HashMap<String, String>)