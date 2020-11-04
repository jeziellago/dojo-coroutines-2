package com.dojo.coroutines

import android.app.Application
import com.dojo.coroutines.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(applicationModules)
        }
    }
}