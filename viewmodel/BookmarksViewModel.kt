package com.dcapp.creds_keeper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.repository.CredRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarksViewModel(private val credRepository: CredRepository) : ViewModel(){
    init {
        viewModelScope.launch(Dispatchers.IO) {
            credRepository.fetchBookmarkedCreds()
        }
    }
    fun getBookmarkCredLiveList() : LiveData<List<Cred>>{
        return credRepository.bookmarkedCreds
    }

    fun insertCred(cred: Cred){
        viewModelScope.launch(Dispatchers.IO) {
            credRepository.insertCred(cred)
        }
    }

    fun updateCred(cred: Cred){
        viewModelScope.launch(Dispatchers.IO){
            credRepository.updateCred(cred)
        }
    }

    fun toggleBookmark(cred : Cred){
        viewModelScope.launch(Dispatchers.IO) {
            credRepository.toggleBookmark(cred)
        }
    }

    fun deleteCred(cred:Cred){
        viewModelScope.launch(Dispatchers.IO){
            credRepository.deleteCred(cred)
        }
    }

    fun searchCred(query : String){
        viewModelScope.launch(Dispatchers.IO){
            credRepository.searchBookmarkedCreds(query)
        }
    }

    fun clearSearch(){
        viewModelScope.launch(Dispatchers.IO) {
            credRepository.clearSearch()
        }
    }
}


//
//class BookmarksViewModel(private val credRepository: CredRepository) : ViewModel(){
//    fun getBookmarkCredLiveList() : LiveData<List<Cred>>{
//        return credRepository.bookmarkedCreds
//    }
//    fun updateCred(cred: Cred){
//        credRepository.addOrUpdateCred(cred)
//    }
//
//    fun toggleBookmark(credId : Int){
//        credRepository.toggleBookmark(credId)
//    }
//
//    fun deleteCred(credId : Int){
//        credRepository.deleteCred(credId)
//    }
//
//    fun searchCred(query : String){
//        credRepository.searchBookmarkedCreds(query)
//    }
//
//    fun clearSearch()= credRepository.clearSearch()
//}

