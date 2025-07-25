package com.example.userinfotask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userinfotask.domain.model.User
import com.example.userinfotask.domain.usecase.GetAllUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * ViewModel for the display screen
 * This follows the MVVM pattern and handles the business logic for displaying users
 * Uses StateFlow for reactive state management and observes data from the use case
 */
class DisplayViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {
    
    /**
     * StateFlow for managing the UI state
     * Contains the list of users and loading/error states
     */
    private val _uiState = MutableStateFlow(DisplayUiState())
    val uiState: StateFlow<DisplayUiState> = _uiState.asStateFlow()
    
    init {
        // Load users when the ViewModel is initialized
        loadUsers()
    }
    
    /**
     * Loads all users from the database
     * Observes the data stream and updates the UI state accordingly
     */
    private fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            getAllUsersUseCase()
                .catch { exception ->
                    // Handle errors in the data stream
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to load users: ${exception.message}"
                    )
                }
                .collect { users ->
                    // Update UI state with the loaded users
                    _uiState.value = _uiState.value.copy(
                        users = users,
                        isLoading = false,
                        errorMessage = null
                    )
                }
        }
    }
    
    /**
     * Refreshes the user list
     * Can be called to reload data from the database
     */
    fun refreshUsers() {
        loadUsers()
    }
    
    /**
     * Clears the error message
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

/**
 * Data class representing the UI state for the display screen
 * Contains the list of users and UI state information
 */
data class DisplayUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) 