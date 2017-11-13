package com.example.juan.kotlintest

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

/**
 * Created by Juan on 12/11/2017.
 */

class AppStatus {
    internal var connectivityManager: ConnectivityManager? = null
    internal var connected = false

    val isOnline: Boolean
        get() {
            try {
                connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val networkInfo = connectivityManager!!.activeNetworkInfo
                connected = networkInfo != null && networkInfo.isAvailable  && networkInfo.isConnected
                return connected


            } catch (e: Exception) {
                println("CheckConnectivity Exception: " + e.message)
                Log.v("connectivity", e.toString())
            }

            return connected
        }

    companion object {

        private val instance = AppStatus()
        internal var context: Context? = null

        fun getInstance(ctx: Context): AppStatus {
            context = ctx.applicationContext
            return instance
        }
    }
}