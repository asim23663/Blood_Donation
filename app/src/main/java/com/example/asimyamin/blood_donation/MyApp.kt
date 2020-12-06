package com.example.asimyamin.blood_donation

import android.app.Application
import android.content.Context

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: MyApp? = null
            private set
        val context: Context
            get() = instance!!.applicationContext
    }
}