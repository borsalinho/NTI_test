package com.s21.appclient.app

import android.app.Application
import com.s21.appclient.di.AppClientComponent
import com.s21.appclient.di.AppClientModule
import com.s21.appclient.di.DaggerAppClientComponent

import com.s21.domain.di.DaggerDomainComponent
import com.s21.domain.di.DomainComponent
import com.s21.domain.di.DomainModule

class AppClient : Application() {
    lateinit var appClientComponent : AppClientComponent

    override fun onCreate() {
        super.onCreate()

        val domainComponent: DomainComponent = DaggerDomainComponent
            .builder()
            .domainModule(DomainModule())
            .build()

        appClientComponent = DaggerAppClientComponent
            .builder()
            .domainComponent(domainComponent)
            .appClientModule(AppClientModule(this))
            .build()
    }
}