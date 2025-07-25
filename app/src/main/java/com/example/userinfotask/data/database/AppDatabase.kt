package com.example.userinfotask.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.userinfotask.data.dao.UserDao
import com.example.userinfotask.data.entity.UserEntity

/**
 * Room database class that serves as the main access point for the database
 * This is a singleton pattern implementation for database access
 * Room will generate the implementation of this abstract class
 */
@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    
    /**
     * Abstract method to get the UserDao
     * Room will generate the implementation
     */
    abstract fun userDao(): UserDao
    
    companion object {
        /**
         * Database name constant
         */
        private const val DATABASE_NAME = "user_info_database"
        
        /**
         * Volatile annotation ensures that changes to INSTANCE are immediately visible to all threads
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        /**
         * Singleton pattern implementation for database access
         * Ensures only one database instance exists throughout the app lifecycle
         * @param context Application context
         * @return AppDatabase instance
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 