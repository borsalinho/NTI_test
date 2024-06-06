package com.s21.appserver.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppServerModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context
}