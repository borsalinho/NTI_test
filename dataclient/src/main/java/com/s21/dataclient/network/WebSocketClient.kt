package com.s21.dataclient.network

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import io.ktor.client.HttpClient
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject


class WebSocketClient @Inject constructor(private val context: Context) {

    private val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = 20_000
        }
    }
    private var webSocketSession: WebSocketSession? = null
    private val sessionMutex = Mutex()

    suspend fun connectToServer() {
        try {
            client.webSocket(host = "10.0.2.2", port = 8080, path = "/echo") {
                Log.d("ClientLog", "я есть соединение с сервером!!")
                webSocketSession = this

//                launch {
//                    while (true){
//                        delay(2000L)
//                        send(Frame.Text("Привет от клиента!!!"))
//                    }
//                }

                for (frame in incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val text = frame.readText()
                            Log.d("ClientLog", "Принято: $text")

                            if (text == "yes_u_can_launch_the_chrome"){

                                Log.d("ClientLog", "пытаюсь запустить хром")
//                                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://")).apply {
//                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                }
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://"))
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                context.startActivity(intent)
//                                context.startActivity(browserIntent)
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
            sessionMutex.withLock {
                if (webSocketSession != null && webSocketSession!!.isActive) {
                    Log.d("ClientLog", "пытаюсь отправить i_want_to_launch_the_chrome")
                    webSocketSession!!.send(Frame.Text("i_want_to_launch_the_chrome"))
                } else {
                    Log.e("ClientLog", "Ошибка: сессия WebSocket равна null или не активна")
                }
            }
        } catch (e: Exception) {
            Log.e("ClientLog", "Ошибка при отправке сообщения: ${e.message}", e)
        }
    }

    suspend fun disconnectToServer() {
        Log.d("ClientLog", "Отключаюсь от сервера")
        webSocketSession?.close()
        webSocketSession = null
        client.close()
    }
}
