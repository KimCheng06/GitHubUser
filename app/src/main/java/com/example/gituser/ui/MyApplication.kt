package com.example.gituser.ui

import android.app.Application
import com.example.gituser.di.apiModule
import com.example.gituser.di.coordinatorModule
import com.example.gituser.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    apiModule,
                    coordinatorModule,
                    viewModelModule
                )
            )
        }
    }

    companion object {
        lateinit var instance: MyApplication
            private set
    }
}