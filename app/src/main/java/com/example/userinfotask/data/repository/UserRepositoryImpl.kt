package com.example.userinfotask.data.repository

import com.example.userinfotask.data.dao.UserDao
import com.example.userinfotask.data.entity.UserEntity
import com.example.userinfotask.domain.model.User
import com.example.userinfotask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of UserRepository interface
 * This class bridges the domain and data layers by converting between domain models and data entities
 * It follows the Repository pattern and Clean Architecture principles
 */
class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    
    /**
     * Inserts a new user into the database
     * Converts domain User model to UserEntity before insertion
     * @param user The domain User model to insert
     * @return The ID of the inserted user
     */
    override suspend fun insertUser(user: User): Long {
        return userDao.insertUser(UserEntity(
            id = user.id,
            name = user.name,
            age = user.age,
            jobTitle = user.jobTitle,
            gender = user.gender.name
        ))
    }
    
    /**
     * Retrieves all users from the database
     * Converts UserEntity list to domain User models
     * @return Flow of list of domain User models
     */
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }
    
    /**
     * Retrieves a specific user by ID
     * Converts UserEntity to domain User model
     * @param id The user ID to search for
     * @return The domain User model if found, null otherwise
     */
    override suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)?.toDomainModel()
    }
    
    /**
     * Updates an existing user in the database
     * Converts domain User model to UserEntity before update
     * @param user The domain User model with updated information
     */
    override suspend fun updateUser(user: User) {
        userDao.updateUser(UserEntity(
            id = user.id,
            name = user.name,
            age = user.age,
            jobTitle = user.jobTitle,
            gender = user.gender.name
        ))
    }
    
    /**
     * Deletes a user by ID
     * @param id The ID of the user to delete
     */
    override suspend fun deleteUser(id: Long) {
        userDao.deleteUser(id)
    }
    
    /**
     * Deletes all users from the database
     */
    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
} 