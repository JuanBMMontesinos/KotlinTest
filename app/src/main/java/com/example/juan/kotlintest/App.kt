package com.example.juan.kotlintest

import android.app.Application
import com.example.juan.kotlintest.mcache.Cache
import com.example.juan.kotlintest.mcache.CacheMode

/**
 * Created by Juan on 13/11/2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Cache
                .withGlobalMode(CacheMode.FILE)
                .with(this)
    }
}