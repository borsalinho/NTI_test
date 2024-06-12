package com.s21.appserver.di


import com.s21.dataserver.implementation.ServerRepositoryImpl
import com.s21.dataserver.network.WebSocketMainClient
import com.s21.domain.repository.ServerRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataServerModule {

    @Provides
    fun provideWebSocketMainClient() : WebSocketMainClient{
        return WebSocketMainClient()
    }

    @Provides
    fun provideServerRepositoryImpl(webSocketClient: WebSocketMainClient) : ServerRepository {
        return ServerRepositoryImpl(webSocketClient = webSocketClient)
    }


}