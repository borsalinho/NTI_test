package com.s21.appclient.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppClientModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context
}