package com.s21.dataserver.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText

class WebSocketMainClient {

    private val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = 20_000
        }
    }

    private var webSocketSession: WebSocketSession? = null

    suspend fun connectWebSocket() {
        try {
            if (webSocketSession == null) {
                client.webSocket(host = "10.0.2.2", port = 8080, path = "/echo") {
                    Log.d("MainClient", "Соединение установлено!")
                    send(Frame.Text("i_am_main_client"))
                    webSocketSession = this

                    for (frame in incoming) {
                        when (frame) {
                            is Frame.Text -> {
                                val text = frame.readText()
                                Log.d("MainClient", "Пришло: $text")
                            }
                            else -> {
                                Log.d("MainClient", "Это не текст!")
                            }
                        }
                    }
                }
            } else {
                Log.d("MainClient", "Cоединение опять установлено!)")
            }
        } catch (e: Exception) {
            Log.e("MainClient", "Ошибка: ${e.message}", e)

        }

    }

    suspend fun disconnectWebSocket() {
        Log.d("MainClient", "Отключаюсь от сервера")
        webSocketSession?.close()
        webSocketSession = null
        client.close()
    }
}