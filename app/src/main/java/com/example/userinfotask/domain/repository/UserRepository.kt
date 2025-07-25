package com.example.userinfotask.domain.repository

import com.example.userinfotask.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface defining the contract for user data operations
 * This follows the Repository pattern and Clean Architecture principles
 * The domain layer depends on this abstraction, not on concrete implementations
 */
interface UserRepository {
    
    /**
     * Inserts a new user into the data source
     * @param user The user to be inserted
     * @return The ID of the inserted user
     */
    suspend fun insertUser(user: User): Long
    
    /**
     * Retrieves all users from the data source
     * @return Flow of list of users for reactive data updates
     */
    fun getAllUsers(): Flow<List<User>>
    
    /**
     * Retrieves a specific user by ID
     * @param id The user ID to search for
     * @return The user if found, null otherwise
     */
    suspend fun getUserById(id: Long): User?
    
    /**
     * Updates an existing user
     * @param user The user with updated information
     */
    suspend fun updateUser(user: User)
    
    /**
     * Deletes a user by ID
     * @param id The ID of the user to delete
     */
    suspend fun deleteUser(id: Long)
    
    /**
     * Deletes all users from the data source
     */
    suspend fun deleteAllUsers()
} 