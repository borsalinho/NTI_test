package com.s21.appserver.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


import androidx.compose.ui.unit.dp
import com.s21.appserver.presentation.viewmodel.ServerViewModel

@Composable
fun ServerMainScreen(serverViewModel: ServerViewModel) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("only 10.0.2.2:8080")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    serverViewModel.startServer()
                }) {
                    Text("Connect to server")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    serverViewModel.stopServer()
                }) {
                    Text("Disconnect from server")
                }
            }
        }
    }
}