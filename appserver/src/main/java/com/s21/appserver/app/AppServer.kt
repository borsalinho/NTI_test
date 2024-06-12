package com.s21.appserver.app

import android.app.Application
import com.s21.appserver.di.AppServerComponent
import com.s21.appserver.di.AppServerModule
import com.s21.appserver.di.DaggerAppServerComponent


class AppServer : Application() {
    lateinit var appServerComponent : AppServerComponent

    override fun onCreate() {
        super.onCreate()

        appServerComponent = DaggerAppServerComponent
            .builder()
            .appServerModule(AppServerModule(this))
            .build()
    }
}