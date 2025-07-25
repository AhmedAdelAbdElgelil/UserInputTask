package com.example.userinfotask.domain.usecase

import com.example.userinfotask.domain.model.User
import com.example.userinfotask.domain.repository.UserRepository

/**
 * Use case for inserting a new user
 * This follows the Single Responsibility Principle and encapsulates business logic
 * Use cases represent the application's business rules and orchestrate domain objects
 */
class InsertUserUseCase(
    private val userRepository: UserRepository
) {
    
    /**
     * Executes the use case to insert a user
     * @param user The user to be inserted
     * @return Result containing the inserted user ID or an error
     */
    suspend operator fun invoke(user: User): Result<Long> {
        return try {
            // Validate user data before insertion
            if (!user.isValid()) {
                return Result.failure(IllegalArgumentException("Invalid user data"))
            }
            
            // Insert user and return the generated ID
            val userId = userRepository.insertUser(user)
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 