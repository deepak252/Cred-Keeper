package com.dcapp.creds_keeper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.repository.CredRepository

class BookmarksViewModel(private val credRepository: CredRepository) : ViewModel(){
    fun getBookmarkCredLiveList() : LiveData<List<Cred>>{
        return credRepository.bookmarkedCreds
    }
    fun updateCred(cred: Cred){
        credRepository.addOrUpdateCred(cred)
    }

    fun toggleBookmark(credId : Int){
        credRepository.toggleBookmark(credId)
    }

    fun deleteCred(credId : Int){
        credRepository.deleteCred(credId)
    }

    fun searchCred(query : String){
        credRepository.searchBookmarkedCreds(query)
    }

    fun clearSearch()= credRepository.clearSearch()
}

