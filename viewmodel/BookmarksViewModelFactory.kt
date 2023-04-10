package com.dcapp.creds_keeper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dcapp.creds_keeper.repository.CredRepository

class BookmarksViewModelFactory(private val credRepository: CredRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookmarksViewModel(credRepository) as T
    }
}