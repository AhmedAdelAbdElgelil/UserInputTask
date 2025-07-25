package com.example.userinfotask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userinfotask.domain.model.User
import com.example.userinfotask.domain.usecase.InsertUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the input screen
 * This follows the MVVM pattern and handles the business logic for user input
 * Uses StateFlow for reactive state management and coroutines for asynchronous operations
 */
class InputViewModel(
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel() {
    
    /**
     * StateFlow for managing the UI state
     * Contains the current state of the input form and any error messages
     */
    private val _uiState = MutableStateFlow(InputUiState())
    val uiState: StateFlow<InputUiState> = _uiState.asStateFlow()
    
    /**
     * StateFlow for navigation events
     * Used to trigger navigation to the display screen after successful save
     */
    private val _navigationEvent = MutableStateFlow<NavigationEvent?>(null)
    val navigationEvent: StateFlow<NavigationEvent?> = _navigationEvent.asStateFlow()
    
    /**
     * Updates the name field in the UI state
     * @param name The new name value
     */
    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }
    
    /**
     * Updates the age field in the UI state
     * @param age The new age value
     */
    fun updateAge(age: String) {
        _uiState.value = _uiState.value.copy(age = age)
    }
    
    /**
     * Updates the job title field in the UI state
     * @param jobTitle The new job title value
     */
    fun updateJobTitle(jobTitle: String) {
        _uiState.value = _uiState.value.copy(jobTitle = jobTitle)
    }
    
    /**
     * Updates the gender selection in the UI state
     * @param gender The selected gender
     */
    fun updateGender(gender: User.Gender) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }
    
    /**
     * Saves the user data to the database
     * Validates the input and handles the save operation asynchronously
     */
    fun saveUser() {
        val currentState = _uiState.value
        
        // Validate input fields
        if (!isInputValid(currentState)) {
            _uiState.value = currentState.copy(
                errorMessage = "Please fill all fields correctly. Age must be a valid number."
            )
            return
        }
        
        // Clear any previous error messages
        _uiState.value = currentState.copy(
            isLoading = true,
            errorMessage = null
        )
        
        // Create user object from input data
        val user = User(
            name = currentState.name.trim(),
            age = currentState.age.toInt(),
            jobTitle = currentState.jobTitle.trim(),
            gender = currentState.gender
        )
        
        // Execute the save operation
        viewModelScope.launch {
            insertUserUseCase(user).fold(
                onSuccess = { userId ->
                    // Success: navigate to display screen
                    _uiState.value = currentState.copy(isLoading = false)
                    _navigationEvent.value = NavigationEvent.NavigateToDisplay
                },
                onFailure = { exception ->
                    // Error: show error message
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        errorMessage = "Failed to save user: ${exception.message}"
                    )
                }
            )
        }
    }
    
    /**
     * Validates the input fields
     * @param state The current UI state
     * @return true if all fields are valid, false otherwise
     */
    private fun isInputValid(state: InputUiState): Boolean {
        return state.name.trim().isNotBlank() &&
               state.age.trim().isNotBlank() &&
               state.age.toIntOrNull() != null &&
               state.age.toInt() > 0 &&
               state.jobTitle.trim().isNotBlank()
    }
    
    /**
     * Clears the navigation event after it has been handled
     */
    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
    
    /**
     * Clears the error message
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

/**
 * Data class representing the UI state for the input screen
 * Contains all the form fields and UI state information
 */
data class InputUiState(
    val name: String = "",
    val age: String = "",
    val jobTitle: String = "",
    val gender: User.Gender = User.Gender.MALE,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

/**
 * Sealed class representing navigation events
 * Used to handle navigation between screens
 */
sealed class NavigationEvent {
    object NavigateToDisplay : NavigationEvent()
} 