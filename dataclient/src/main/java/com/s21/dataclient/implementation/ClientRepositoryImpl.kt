package com.s21.dataclient.implementation

import com.s21.dataclient.network.WebSocketClient
import com.s21.domain.repository.ClientRepository

class ClientRepositoryImpl(private val webSocketClient: WebSocketClient) : ClientRepository {
    override suspend fun connectToServer() {
        webSocketClient.connectToServer()
    }

    override suspend fun pause() {
        webSocketClient.pause()
    }

    override suspend fun launchChrome() {
        webSocketClient.launchChrome()
    }

    override suspend fun disconnectToServer() {
        webSocketClient.disconnectToServer()
    }
}