package com.dcapp.creds_keeper.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.repository.CredRepository
import com.dcapp.creds_keeper.repository.creds

class HomeViewModel(private val credRepository: CredRepository ) : ViewModel() {

    fun getCredLiveList() : LiveData<List<Cred>>{
        return credRepository.allCreds
    }

    fun addOrUpdateCred(cred: Cred){
        credRepository.addOrUpdateCred(cred)
    }

    fun toggleBookmark(credId : Int){
        credRepository.toggleBookmark(credId)
    }

    fun deleteCred(credId : Int){
        credRepository.deleteCred(credId)
    }

    fun searchCred(query : String){
        credRepository.searchAllCreds(query)
    }

    fun clearSearch()= credRepository.clearSearch()

}