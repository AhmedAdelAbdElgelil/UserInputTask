package com.example.userinfotask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.userinfotask.databinding.ActivityMainBinding

/**
 * Main Activity that hosts the navigation fragments
 * This is the single activity in the app that uses Navigation Component
 * for fragment-based navigation as per the requirements
 */
class MainActivity : AppCompatActivity() {
    
    /**
     * ViewBinding for safe view access
     */
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Setup ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Setup Navigation Component
        setupNavigation()
    }
    
    /**
     * Sets up the Navigation Component with the NavHostFragment
     * This enables fragment-based navigation throughout the app
     */
    private fun setupNavigation() {
        // Get the NavHostFragment from the layout
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        
        // Get the NavController from the NavHostFragment
        val navController = navHostFragment.navController
        
        // Note: ActionBar setup removed since we're using NoActionBar theme
        // Navigation will work without ActionBar integration
    }
    
    /**
     * Handles the up button in the ActionBar
     * This allows users to navigate back using the system back button
     * @return true if the navigation was handled, false otherwise
     */
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}