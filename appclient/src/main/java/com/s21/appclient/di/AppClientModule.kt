package com.s21.appclient.di

import android.content.Context
import com.s21.appclient.presentation.viewmodel.ClientViewModel
import com.s21.domain.usecases.LaunchChromeUseCase
import com.s21.domain.usecases.PauseClientUseCase
import com.s21.domain.usecases.StartClientUseCase
import com.s21.domain.usecases.StopClientUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppClientModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideClientViewModel(
        launchChromeUseCase : LaunchChromeUseCase,
        pauseClientUseCase : PauseClientUseCase,
        startClientUseCase: StartClientUseCase,
        stopClientUseCase: StopClientUseCase
    ) : ClientViewModel {
        return ClientViewModel(
            launchChromeUseCase = launchChromeUseCase,
            pauseClientUseCase = pauseClientUseCase,
            startClientUseCase = startClientUseCase,
            stopClientUseCase = stopClientUseCase
        )
    }
}