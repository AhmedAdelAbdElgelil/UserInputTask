package com.example.userinfotask.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinfotask.App
import com.example.userinfotask.databinding.FragmentDisplayBinding
import com.example.userinfotask.presentation.adapter.UserAdapter
import com.example.userinfotask.presentation.viewmodel.DisplayViewModel
import kotlinx.coroutines.launch

/**
 * Fragment for displaying the list of users
 * This fragment shows all users stored in the database using RecyclerView
 * Follows MVVM pattern and uses ViewBinding for view access
 */
class DisplayFragment : Fragment() {
    
    /**
     * ViewBinding for safe view access
     */
    private var _binding: FragmentDisplayBinding? = null
    private val binding get() = _binding!!
    
    /**
     * ViewModel for handling business logic
     * Created manually and scoped to this fragment
     */
    private lateinit var viewModel: DisplayViewModel
    
    /**
     * RecyclerView adapter for displaying users
     */
    private lateinit var userAdapter: UserAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using ViewBinding
        _binding = FragmentDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModel
        val app = requireActivity().application as App
        viewModel = DisplayViewModel(app.container.getAllUsersUseCase)
        
        // Setup RecyclerView
        setupRecyclerView()
        
        // Observe ViewModel state
        observeViewModel()
    }
    
    /**
     * Sets up the RecyclerView with adapter and layout manager
     */
    private fun setupRecyclerView() {
        // Create and set the adapter
        userAdapter = UserAdapter()
        binding.rvUsers.adapter = userAdapter
        
        // Set layout manager for vertical scrolling
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        
        // Optional: Add item decoration for spacing
        // binding.rvUsers.addItemDecoration(
        //     DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        // )
    }
    
    /**
     * Observes the ViewModel state and updates the UI accordingly
     */
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                // Update loading state
                binding.progressBar.isVisible = state.isLoading
                
                // Update error message
                if (state.errorMessage != null) {
                    binding.tvErrorMessage.text = state.errorMessage
                    binding.tvErrorMessage.isVisible = true
                    binding.rvUsers.isVisible = false
                    binding.emptyState.isVisible = false
                } else {
                    binding.tvErrorMessage.isVisible = false
                    
                    // Update user list
                    userAdapter.submitList(state.users)
                    
                    // Show/hide empty state
                    if (state.users.isEmpty() && !state.isLoading) {
                        binding.emptyState.isVisible = true
                        binding.rvUsers.isVisible = false
                    } else {
                        binding.emptyState.isVisible = false
                        binding.rvUsers.isVisible = true
                    }
                }
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up ViewBinding to prevent memory leaks
        _binding = null
    }
} 