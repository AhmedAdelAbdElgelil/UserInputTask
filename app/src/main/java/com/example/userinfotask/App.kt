package com.example.userinfotask

import android.app.Application
import com.example.userinfotask.di.AppContainer

/**
 * Application class that initializes the dependency injection container
 * This is the entry point for the application
 */
class App : Application() {
    
    /**
     * Dependency injection container
     */
    lateinit var container: AppContainer
        private set
    
    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
} 