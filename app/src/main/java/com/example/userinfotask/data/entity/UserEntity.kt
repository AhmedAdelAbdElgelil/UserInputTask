package com.example.userinfotask.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.userinfotask.domain.model.User

/**
 * Room entity representing the user table in the database
 * This is the data layer representation of the User domain model
 * Room annotations define the table structure and relationships
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: String // Stored as string in database, converted to enum in domain
) {
    
    /**
     * Extension function to convert UserEntity to domain User model
     * This maintains separation between data and domain layers
     */
    fun toDomainModel(): User {
        return User(
            id = id,
            name = name,
            age = age,
            jobTitle = jobTitle,
            gender = User.Gender.valueOf(gender)
        )
    }
    
    companion object {
        /**
         * Extension function to convert domain User model to UserEntity
         * This maintains separation between domain and data layers
         */
        fun User.toEntity(): UserEntity {
            return UserEntity(
                id = id,
                name = name,
                age = age,
                jobTitle = jobTitle,
                gender = gender.name
            )
        }
    }
} 