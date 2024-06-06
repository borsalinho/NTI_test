package com.s21.appclient.di


import com.s21.appclient.ui.MainClientActivity
import com.s21.domain.di.DomainComponent

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        DomainComponent::class
    ],
    modules = [
        AppClientModule::class,
        DataClientModule::class
    ]
)
interface AppClientComponent {
    fun inject(mainClientActivity: MainClientActivity)

}