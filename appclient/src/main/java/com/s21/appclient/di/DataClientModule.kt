package com.s21.appclient.di

import android.content.Context
import com.s21.dataclient.implementation.ClientRepositoryImpl
import com.s21.dataclient.network.WebSocketClient
import com.s21.domain.repository.ClientRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataClientModule {

    @Provides
    @Singleton
    fun provideWebSocketMainClient(context: Context) : WebSocketClient {
        return WebSocketClient(context = context)
    }

    @Provides
    @Singleton
    fun provideServerRepositoryImpl(webSocketClient: WebSocketClient) : ClientRepository {
        return ClientRepositoryImpl(webSocketClient = webSocketClient)
    }
}