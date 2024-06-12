package com.s21.appserver.ui


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.lifecycle.lifecycleScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket


import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class ServerMainActivity : ComponentActivity() {

    private val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = 20_000
        }
    }

    private var webSocketSession: WebSocketSession? = null

    private val clientRequests = mutableMapOf<String, String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("I am a SERVER-CLIENT")
        }
        connectWebSocket()
    }

    private fun connectWebSocket() {

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                client.webSocket(host = "10.0.2.2", port = 8080, path = "/echo") {
                    Log.d("MainClientLog", "Connected to WebSocket server")
                    send(Frame.Text("i_am_main_client"))
                    webSocketSession = this

                    for (frame in incoming) {
                        when (frame) {
                            is Frame.Text -> {
                                val text = frame.readText()
                                Log.d("MainClientLog", "Получено: $text")
                                if (text.startsWith("from_client:")){
                                    val clientId = text.split(":")[1]
                                    clientRequests[clientId] = text
                                    send(Frame.Text("response_for:$clientId: сообщение от главного клиента для клиента №$clientId"))

                                    Log.d("MainClientLog", "Отправлено: $text")
                                }
                                // приходит from_clent:№:text

                            }

                            else -> {
                                Log.d("MainClientLog", "Received non-text frame")
                            }
                        }
                    }


                }
            } catch (e: Exception) {
                Log.e("MainClientLog", "Error during WebSocket connection: ${e.message}", e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch {
            client.close()
        }
    }

}

//                    launch {
//                        while (true) {
//                            delay(5000L)
//                            send(Frame.Text("Привет от главного клиента(сервера)!!!"))
//                        }
//                    }

//for (frame in incoming) {
//    when (frame) {
//        is Frame.Text -> {
//            val text = frame.readText()
//            Log.d("WebSocket", "Received: $text")
//        }
//
//        else -> {
//            Log.d("WebSocket", "Received non-text frame")
//        }
//    }
//}






