package com.s21.appserver.di

import com.s21.domain.repository.ServerRepository
import com.s21.domain.usecases.StartServerUseCase
import com.s21.domain.usecases.StopServerUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideStartServerUseCase(serverRepository : ServerRepository) : StartServerUseCase {
        return StartServerUseCase(serverRepository = serverRepository)
    }

    @Provides
    @Singleton
    fun provideStopServerUseCase(serverRepository : ServerRepository) : StopServerUseCase {
        return StopServerUseCase(serverRepository = serverRepository)
    }

}