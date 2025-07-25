package com.example.userinfotask.presentation.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.userinfotask.App
import com.example.userinfotask.R
import com.example.userinfotask.databinding.FragmentInputBinding
import com.example.userinfotask.domain.model.User
import com.example.userinfotask.presentation.viewmodel.InputViewModel
import com.example.userinfotask.presentation.viewmodel.NavigationEvent
import kotlinx.coroutines.launch

/**
 * Fragment for user input form
 * This fragment handles user data input and validation
 * Follows MVVM pattern and uses ViewBinding for view access
 */
class InputFragment : Fragment() {
    
    /**
     * ViewBinding for safe view access
     */
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    
    /**
     * ViewModel for handling business logic
     * Created manually and scoped to this fragment
     */
    private lateinit var viewModel: InputViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using ViewBinding
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModel
        val app = requireActivity().application as App
        viewModel = InputViewModel(app.container.insertUserUseCase)
        
        // Setup UI components
        setupViews()
        
        // Observe ViewModel state
        observeViewModel()
        
        // Observe navigation events
        observeNavigationEvents()
    }
    
    /**
     * Sets up the UI components and their event listeners
     */
    private fun setupViews() {
        // Setup text change listeners for real-time validation
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateName(s?.toString() ?: "")
            }
        })
        
        binding.etAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateAge(s?.toString() ?: "")
            }
        })
        
        binding.etJobTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateJobTitle(s?.toString() ?: "")
            }
        })
        
        // Setup gender selection
        binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
            val gender = when (checkedId) {
                binding.rbMale.id -> User.Gender.MALE
                binding.rbFemale.id -> User.Gender.FEMALE
                else -> User.Gender.MALE
            }
            viewModel.updateGender(gender)
        }
        
        // Setup save button click listener
        binding.btnSave.setOnClickListener {
            viewModel.saveUser()
        }
    }
    
    /**
     * Observes the ViewModel state and updates the UI accordingly
     */
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                // Update loading state
                binding.progressBar.isVisible = state.isLoading
                binding.btnSave.isEnabled = !state.isLoading
                
                // Update error message
                if (state.errorMessage != null) {
                    binding.tvErrorMessage.text = state.errorMessage
                    binding.tvErrorMessage.isVisible = true
                    Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
                } else {
                    binding.tvErrorMessage.isVisible = false
                }
            }
        }
    }
    
    /**
     * Observes navigation events and handles navigation
     */
    private fun observeNavigationEvents() {
                    viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigationEvent.collect { event ->
                event?.let {
                    when (it) {
                        is NavigationEvent.NavigateToDisplay -> {
                            // Navigate to display screen
                            findNavController().navigate(R.id.displayFragment)
                            viewModel.clearNavigationEvent()
                        }
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