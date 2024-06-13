package com.s21.appclient.di

import com.s21.domain.repository.ClientRepository
import com.s21.domain.usecases.LaunchChromeUseCase
import com.s21.domain.usecases.PauseClientUseCase
import com.s21.domain.usecases.StartClientUseCase
import com.s21.domain.usecases.StopClientUseCase

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideStartClientUseCase(clientRepository : ClientRepository) : StartClientUseCase {
        return StartClientUseCase(clientRepository = clientRepository)
    }

    @Provides
    @Singleton
    fun providePauseClientUseCase(clientRepository : ClientRepository) : PauseClientUseCase {
        return PauseClientUseCase(clientRepository = clientRepository)
    }

    @Provides
    @Singleton
    fun provideStopClientUseCase(clientRepository : ClientRepository) : StopClientUseCase {
        return StopClientUseCase(clientRepository = clientRepository)
    }

    @Provides
    @Singleton
    fun provideLaunchChromeUseCase(clientRepository : ClientRepository) : LaunchChromeUseCase {
        return LaunchChromeUseCase(clientRepository = clientRepository)
    }
}