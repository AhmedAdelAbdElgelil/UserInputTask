package com.example.userinfotask.data.dao

import androidx.room.*
import com.example.userinfotask.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for User entity
 * Defines the database operations that can be performed on the users table
 * Room will generate the implementation of these methods
 */
@Dao
interface UserDao {
    
    /**
     * Inserts a new user into the database
     * @param user The user entity to insert
     * @return The ID of the inserted user
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long
    
    /**
     * Retrieves all users from the database
     * @return Flow of list of user entities for reactive data updates
     */
    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAllUsers(): Flow<List<UserEntity>>
    
    /**
     * Retrieves a specific user by ID
     * @param id The user ID to search for
     * @return The user entity if found, null otherwise
     */
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): UserEntity?
    
    /**
     * Updates an existing user in the database
     * @param user The user entity with updated information
     */
    @Update
    suspend fun updateUser(user: UserEntity)
    
    /**
     * Deletes a user by ID
     * @param id The ID of the user to delete
     */
    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUser(id: Long)
    
    /**
     * Deletes all users from the database
     */
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
} 