package com.s21.appclient.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.LaunchChromeUseCase
import com.s21.domain.usecases.PauseClientUseCase
import com.s21.domain.usecases.StartClientUseCase
import com.s21.domain.usecases.StopClientUseCase

import kotlinx.coroutines.launch

class ClientViewModel (
    private val launchChromeUseCase : LaunchChromeUseCase,
    private val pauseClientUseCase : PauseClientUseCase,
    private val startClientUseCase: StartClientUseCase,
    private val stopClientUseCase: StopClientUseCase
) : ViewModel() {

    fun startServer() {
        viewModelScope.launch {
            startClientUseCase.execute()
        }

    }

    fun pause() {
        viewModelScope.launch {
            pauseClientUseCase.execute()
        }
    }

    fun launchChrome() {
        viewModelScope.launch {
            launchChromeUseCase.execute()
        }
    }

    fun stopServer() {
        viewModelScope.launch {
            stopClientUseCase.execute()
        }
    }
}