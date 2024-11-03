package com.studentdashboard

import android.app.Application
import com.studentdashboard.di.dataStoresModule
import com.studentdashboard.di.utilsModule
import com.studentdashboard.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * MyApplication is the main application class for the Student Dashboard app.
 *
 * This class extends [Application] and serves as the entry point for the app's
 * initialization logic. It is responsible for setting up dependency injection
 * using Koin, allowing the app to manage its dependencies efficiently.
 *
 * Koin Modules:
 * - viewModelsModule: Contains definitions for ViewModels used throughout the app.
 * - dataStoresModule: Manages the data storage solutions, including UserDataStore
 *   and other datastore modules.
 * - utilsModule: Provides utility classes and functions used across the application.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    /**
     * Initializes Koin for dependency injection, setting the application context
     * and loading the required modules.
     */
    private fun initializeKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(
                viewModelsModule,
                dataStoresModule,
                utilsModule
            )
        }
    }
}
