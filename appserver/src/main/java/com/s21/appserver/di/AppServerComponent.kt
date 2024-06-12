package com.s21.appserver.di

import com.s21.appserver.ui.ServerMainActivity
import com.s21.domain.di.DomainComponent
import dagger.Component
import javax.inject.Singleton

interface AppServerComponent {
    @Singleton
    @Component(
        dependencies = [
            DomainComponent::class
        ],
        modules = [
            AppServerModule::class,
            DataServerModule::class
        ]
    )
    interface AppClientComponent : AppServerComponent {
        fun inject(mainServerActivity: ServerMainActivity)

    }
}