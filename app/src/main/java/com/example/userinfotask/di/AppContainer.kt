package com.example.userinfotask.di

import android.content.Context
import com.example.userinfotask.data.dao.UserDao
import com.example.userinfotask.data.database.AppDatabase
import com.example.userinfotask.data.repository.UserRepositoryImpl
import com.example.userinfotask.domain.repository.UserRepository
import com.example.userinfotask.domain.usecase.GetAllUsersUseCase
import com.example.userinfotask.domain.usecase.InsertUserUseCase

/**
 * Simple dependency injection container
 * This replaces Hilt temporarily to resolve compatibility issues
 * Provides all necessary dependencies for the application
 */
class AppContainer(private val context: Context) {
    
    /**
     * Database instance
     */
    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }
    
    /**
     * User DAO instance
     */
    val userDao: UserDao by lazy {
        database.userDao()
    }
    
    /**
     * User repository implementation
     */
    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userDao)
    }
    
    /**
     * Use cases
     */
    val insertUserUseCase: InsertUserUseCase by lazy {
        InsertUserUseCase(userRepository)
    }
    
    val getAllUsersUseCase: GetAllUsersUseCase by lazy {
        GetAllUsersUseCase(userRepository)
    }
} 