package com.s21.appserver.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.s21.appserver.app.AppServer
import com.s21.appserver.presentation.ui.ServerMainScreen
import com.s21.appserver.presentation.viewmodel.ServerViewModel
import javax.inject.Inject


class ServerMainActivity : ComponentActivity() {

    @Inject
    lateinit var serverViewModel: ServerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val appServer = application as AppServer
        appServer.appServerComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            ServerMainScreen(serverViewModel)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        serverViewModel.stopServer()
    }
}
