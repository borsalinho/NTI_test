package com.s21.appclient.app

import android.app.Application
import com.s21.appclient.di.AppClientComponent
import com.s21.appclient.di.AppClientModule
import com.s21.appclient.di.DaggerAppClientComponent


class AppClient : Application() {
    lateinit var appClientComponent : AppClientComponent

    override fun onCreate() {
        super.onCreate()

        appClientComponent = DaggerAppClientComponent
            .builder()
            .appClientModule(AppClientModule(this))
            .build()
    }
}