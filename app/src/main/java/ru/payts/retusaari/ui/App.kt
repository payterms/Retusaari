package ru.payts.retusaari.ui

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.payts.retusaari.di.appModule
import ru.payts.retusaari.di.mainModule
import ru.payts.retusaari.di.noteModule
import ru.payts.retusaari.di.splashModule
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}