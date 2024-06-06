package com.s21.appserver.app

import android.app.Application
import com.s21.appserver.di.AppServerComponent
import com.s21.appserver.di.AppServerModule
import com.s21.appserver.di.DaggerAppServerComponent_AppClientComponent
//import com.s21.appserver.di.DaggerAppServerComponent
import com.s21.domain.di.DaggerDomainComponent

import com.s21.domain.di.DomainComponent
import com.s21.domain.di.DomainModule

class AppServer : Application() {
    lateinit var appServerComponent : AppServerComponent

    override fun onCreate() {
        super.onCreate()

        val domainComponent: DomainComponent = DaggerDomainComponent
            .builder()
            .domainModule(DomainModule())
            .build()
        appServerComponent = DaggerAppServerComponent_AppClientComponent
            .builder()
            .domainComponent(domainComponent)
            .appServerModule(AppServerModule(this))
            .build()
    }
}