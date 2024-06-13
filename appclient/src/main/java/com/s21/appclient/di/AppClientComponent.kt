package com.s21.appclient.di

import com.s21.appclient.ui.ClientMainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppClientModule::class,
        DataClientModule::class,
        DomainModule::class
    ]
)
interface AppClientComponent {
    fun inject(mainClientActivity: ClientMainActivity)

}