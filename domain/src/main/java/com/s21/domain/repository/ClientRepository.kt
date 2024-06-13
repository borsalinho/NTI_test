package com.s21.domain.repository

interface ClientRepository {
    suspend fun connectToServer()
    suspend fun pause()
    suspend fun launchChrome()
    suspend fun disconnectToServer()
}