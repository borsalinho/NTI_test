package com.s21.domain.repository

interface ServerRepository {
    suspend fun connectToServer()
    suspend fun disconnectFromServer()
}