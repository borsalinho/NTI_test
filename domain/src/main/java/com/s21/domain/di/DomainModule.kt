package com.s21.domain.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    fun provideDomainDependency() : ServerDependency {
        return ServerDependency()
    }

    @Provides
    fun provideClientDependency() : ClientDependency {
        return ClientDependency()
    }
}