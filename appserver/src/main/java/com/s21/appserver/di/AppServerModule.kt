package com.s21.appserver.di

import android.content.Context
import com.s21.appserver.presentation.viewmodel.ServerViewModel
import com.s21.domain.usecases.StartServerUseCase
import com.s21.domain.usecases.StopServerUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppServerModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideServerViewModel(
        startServerUseCase: StartServerUseCase,
        stopServerUseCase : StopServerUseCase
    ) : ServerViewModel {
        return ServerViewModel(
            startServerUseCase = startServerUseCase,
            stopServerUseCase = stopServerUseCase
            )
    }
}