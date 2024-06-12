package com.s21.domain.usecases

import com.s21.domain.repository.ServerRepository

class StartServerUseCase(private val serverRepository: ServerRepository) {
    suspend fun execute(){
        serverRepository.connectToServer()
    }
}