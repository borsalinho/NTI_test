package com.s21.localserveronmac.localserver


import android.os.Build
import androidx.annotation.RequiresApi
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import io.ktor.server.websocket.*
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

@RequiresApi(Build.VERSION_CODES.O)
fun main(){
    LocalServer()
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalServer() {
    var mainClientSession: WebSocketSession? = null
    val clients = ConcurrentHashMap<WebSocketSession, String>()

    embeddedServer(Netty, port = 8080, host = "localhost") {
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(15)
            timeout = Duration.ofSeconds(15)
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }
        routing {

            webSocket("/echo") {


                try {
                    val initialMessage = (incoming.receive() as? Frame.Text)?.readText()
                    if (initialMessage == "i_am_main_client") {
                        mainClientSession = this
                        send(Frame.Text("Я теперь главный клиент"))
                    } else {
                        clients[this] = this.hashCode().toString()
                    }

                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            val receivedText = frame.readText()

                            if (receivedText.startsWith("response_for:")) {
                                // приходит от гл клиента
                                // response_for:№: сообщение от главного клиента для клиента"
                                val parts = receivedText.split(":")
                                val clientId = parts[1]
                                val response = parts[2]
                                val clientSession = clients.entries.find { it.value == clientId }?.key
                                clientSession?.send(Frame.Text(response))
                            }

                            else if (mainClientSession != null && mainClientSession != this) {
                                // от клиентов приходит любое сообщение и идет к гл серверу
                                mainClientSession?.send(Frame.Text("from_client:${clients[this]}: $receivedText"))
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    clients.remove(this)
                }
            }

        }
    }.start(wait = true)
}

//launch {
//    while (true) {
//        delay(5000L)
//        send(Frame.Text("Привет от сервера"))
//    }
//}
//
//for (frame in incoming) {
//    when (frame) {
//        is Frame.Text -> {
//            val text = frame.readText()
////                            println("Received: $text")
//        }
//        else -> {
////                            println("Received non-text frame")
//        }
//    }
//}