package com.example.gituser.di

import com.example.gituser.coordinator.Coordinator
import com.example.gituser.coordinator.Navigator
import org.koin.dsl.module

val coordinatorModule = module{
    single { Navigator() }

    single { Coordinator(get()) }
}