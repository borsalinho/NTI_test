package com.s21.domain.di

import dagger.Component


@Component(modules = [DomainModule::class])
interface DomainComponent {
    fun getClientDependency() : ClientDependency
    fun getServerDependency() : ServerDependency

}
