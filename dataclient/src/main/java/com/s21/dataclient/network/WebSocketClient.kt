package com.s21.dataclient.network

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject


class WebSocketClient @Inject constructor(private val context: Context) {

    private val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = 20_000
        }
    }
    private var webSocketSession: WebSocketSession? = null

    suspend fun connectToServer() {
        try {
            client.webSocket(host = "10.0.2.2", port = 8080, path = "/echo") {
                Log.d("ClientLog", "я есть соединение с сервером!!")
                webSocketSession = this

                launch{
                    send(Frame.Text("Я клиент, и я подключюсь к тебе!!")) // заплатка для установления соединяние))
                }

                for (frame in incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val text = frame.readText()
                            Log.d("ClientLog", "Принято: $text")
                            if (text == "yes_u_can_launch_the_chrome"){
                                Log.d("ClientLog", "пытаюсь запустить хром")
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                context.startActivity(intent)
                            }
                        }
                        else -> {
                            Log.d("ClientLog", "не текст")
                        }
                    }
                }

            }
        } catch (e: Exception) {
            Log.e("ClientLog", "какая-то ошибка: ${e.message}", e)
        }
    }

    suspend fun pause() {
        Log.e("ClientLog", "я пауза, но не реализованная")
    }
    suspend fun launchChrome() {
        try {

            if (webSocketSession != null && webSocketSession!!.isActive) {
                Log.d("ClientLog", "пытаюсь отправить i_want_to_launch_the_chrome")
                webSocketSession!!.send(Frame.Text("i_want_to_launch_the_chrome"))
            } else {
                Log.e("ClientLog", "Ошибка: сессия WebSocket равна null или не активна")
            }

        } catch (e: Exception) {
            Log.e("ClientLog", "Ошибка при отправке i_want_to_launch_the_chrome: ${e.message}", e)
        }
    }

    suspend fun disconnectToServer() {
        Log.d("ClientLog", "Отключаюсь от сервера")
        webSocketSession?.close()
        webSocketSession = null
        client.close()
    }
}
