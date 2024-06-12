package com.s21.dataserver.implementation

import com.s21.dataserver.network.WebSocketMainClient
import com.s21.domain.repository.ServerRepository

class ServerRepositoryImpl(private val webSocketClient: WebSocketMainClient) : ServerRepository {

    override suspend fun connectToServer() {
        webSocketClient.connectWebSocket()
    }

    override suspend fun disconnectFromServer() {
        webSocketClient.disconnectWebSocket()
    }
}