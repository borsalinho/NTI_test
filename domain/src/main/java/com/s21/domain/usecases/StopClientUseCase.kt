package com.s21.domain.usecases

import com.s21.domain.repository.ClientRepository

class StopClientUseCase(private val clientRepository: ClientRepository) {
    suspend fun execute(){
        clientRepository.disconnectToServer()
    }
}