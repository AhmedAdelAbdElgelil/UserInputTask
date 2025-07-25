package com.example.userinfotask.domain.model

/**
 * Domain model representing a User entity
 * This is the core business model that follows Clean Architecture principles
 * and is independent of any framework or external dependencies
 */
data class User(
    val id: Long = 0, // Auto-generated ID for Room database
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Gender
) {
    /**
     * Enum representing the gender options
     * This keeps the domain model self-contained and type-safe
     */
    enum class Gender {
        MALE, FEMALE
    }
    
    /**
     * Validation function to ensure data integrity
     * Returns true if the user data is valid according to business rules
     */
    fun isValid(): Boolean {
        return name.isNotBlank() && 
               age > 0 && 
               age <= 150 && 
               jobTitle.isNotBlank()
    }
} 