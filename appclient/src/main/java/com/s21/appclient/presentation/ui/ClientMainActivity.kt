package com.s21.appclient.ui

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.lifecycle.lifecycleScope


import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ClientMainActivity : ComponentActivity() {

    private val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = 20_000
        }
    }

    private var webSocketSession: WebSocketSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("I am a clent")
            Button(onClick = { sendMessage("i_want_to_launch_chrome") }) {
                Text("launch Chrome")
            }
        }
        connectWebSocket()
    }

    private fun connectWebSocket() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                client.webSocket(host = "10.0.2.2", port = 8080, path = "/echo") {
                    Log.d("ClientLog", "Connected to WebSocket server")
                    webSocketSession = this
                    launch {
                        while (true) {
                            delay(5000L)
                            send(Frame.Text("Привет от клиента!!!"))
                        }
                    }

                    for (frame in incoming) {
                        when (frame) {
                            is Frame.Text -> {
                                val text = frame.readText()
                                Log.d("ClientLog", "Received: $text")
                            }

                            else -> {
                                Log.d("ClientLog", "Received non-text frame")
                            }
                        }
                    }


                }
            } catch (e: Exception) {
                Log.e("ClientLog", "Error during WebSocket connection: ${e.message}", e)
            }
        }
    }

    private fun sendMessage(message: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            webSocketSession?.send(Frame.Text(message)) ?: Log.e("ClientLog", "WebSocket session is null")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch {
            client.close()
        }
    }

}


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


