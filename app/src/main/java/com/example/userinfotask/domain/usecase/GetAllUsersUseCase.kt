package com.example.userinfotask.domain.usecase

import com.example.userinfotask.domain.model.User
import com.example.userinfotask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving all users
 * This follows the Single Responsibility Principle and encapsulates business logic
 * Use cases represent the application's business rules and orchestrate domain objects
 */
class GetAllUsersUseCase(
    private val userRepository: UserRepository
) {
    
    /**
     * Executes the use case to get all users
     * @return Flow of list of users for reactive data updates
     */
    operator fun invoke(): Flow<List<User>> {
        return userRepository.getAllUsers()
    }
} 