package com.example.userinfotask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userinfotask.databinding.ItemUserBinding
import com.example.userinfotask.domain.model.User

/**
 * RecyclerView adapter for displaying user information
 * Uses ListAdapter for efficient list updates and DiffUtil for optimal performance
 * Implements the ViewHolder pattern for better memory management
 */
class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {
    
    /**
     * Creates and returns a new UserViewHolder
     * @param parent The ViewGroup into which the new View will be added
     * @param viewType The view type of the new View
     * @return A new UserViewHolder that holds a View
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }
    
    /**
     * Binds the data at the specified position to the ViewHolder
     * @param holder The ViewHolder to bind data to
     * @param position The position of the item in the data set
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    /**
     * ViewHolder class that holds references to the views in each item
     * This pattern improves performance by avoiding repeated findViewById calls
     */
    class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        /**
         * Binds user data to the views
         * @param user The user data to display
         */
        fun bind(user: User) {
            binding.apply {
                // Set user information to the views
                tvUserName.text = user.name
                tvUserAge.text = "Age: ${user.age}"
                tvUserJobTitle.text = user.jobTitle
                tvUserGender.text = "Gender: ${user.gender.name}"
            }
        }
    }
}

/**
 * DiffUtil callback for efficient list updates
 * Compares old and new items to determine what has changed
 * This allows RecyclerView to animate only the necessary changes
 */
class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    
    /**
     * Checks if the items are the same (same ID)
     * @param oldItem The old item
     * @param newItem The new item
     * @return true if the items represent the same entity
     */
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
    
    /**
     * Checks if the items have the same content
     * @param oldItem The old item
     * @param newItem The new item
     * @return true if the items have the same content
     */
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
} 