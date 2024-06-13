package com.s21.appserver.di

import com.s21.appserver.ui.ServerMainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppServerModule::class,
        DataServerModule::class,
        DomainModule::class
    ]
)
interface AppServerComponent {
    fun inject(mainServerActivity: ServerMainActivity)

}