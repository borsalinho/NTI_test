package com.s21.appclient.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.s21.appclient.app.AppClient
import com.s21.appclient.presentation.ui.ClientMainScreen
import com.s21.appclient.presentation.viewmodel.ClientViewModel

import javax.inject.Inject

class ClientMainActivity : ComponentActivity(){
    @Inject
    lateinit var clientViewModel: ClientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val appServer = application as AppClient
        appServer.appClientComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            ClientMainScreen(clientViewModel)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        clientViewModel.stopServer()
    }
}
