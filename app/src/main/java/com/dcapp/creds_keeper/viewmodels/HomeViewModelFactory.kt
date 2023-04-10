package com.dcapp.creds_keeper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dcapp.creds_keeper.repository.CredRepository

class HomeViewModelFactory(private val credRepository: CredRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(credRepository) as T
    }
}