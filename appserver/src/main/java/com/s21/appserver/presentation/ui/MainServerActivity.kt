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
import com.typesafe.config.ConfigException.IO


import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.websocket.*
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Duration


class MainServerActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("IAMALOG","i am alive?")
        setContent {
            Text("I am a SERVER")
            lifecycleScope.launch(Dispatchers.IO) {
                Log.e("IAMALOG","lifecycleScope.launch")
                startServer()
            }
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
suspend fun startServer() {
    try {
        Log.e("IAMALOG", "startServer")
        embeddedServer(Netty, port = 8080) {
            Log.e("IAMALOG", "try install websocket")
            install(WebSockets) {
                pingPeriod = Duration.ofSeconds(30)
                timeout = Duration.ofSeconds(60)
                maxFrameSize = Long.MAX_VALUE
                masking = false
            }
            Log.e("IAMALOG", "websocket installed")
            Log.e("IAMALOG", "try to routing?")
            routing {
//                get("/") {
//                    call.respondText("Hello, world!")
//                }
                Log.e("IAMALOG", "try to webSocket?")
                webSocket("/echo") {
                    Log.e("IAMALOG", "creating webSocket?")

                    launch {
                        while (true) {
                            delay(5000L)
                            send(Frame.Text("Привет от сервера"))
                        }
                    }




                        for (frame in incoming) {
                            when (frame) {
                                is Frame.Text -> {
                                    val text = frame.readText()
                                    Log.d("IAMALOG", "Received: $text")
                                }
                                else -> {
                                    Log.d("IAMALOG", "Received non-text frame")
                                }
                            }
                        }


                }
                Log.e("IAMALOG", "webSocket created?")
            }
        }.start(wait = true)
    } catch (e: Exception) {
        Log.e("IAMALOG", "Exception in startServer", e)
    }
}



