package com.s21.appserver.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.StartServerUseCase
import com.s21.domain.usecases.StopServerUseCase
import kotlinx.coroutines.launch

class ServerViewModel(
    private val startServerUseCase: StartServerUseCase,
    private val stopServerUseCase: StopServerUseCase,
) : ViewModel() {
    fun startServer() {
        viewModelScope.launch {
            startServerUseCase.execute()
        }
    }

    fun stopServer(){
        viewModelScope.launch {
            stopServerUseCase.execute()
        }
    }
}