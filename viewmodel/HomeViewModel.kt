package com.dcapp.creds_keeper.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.repository.CredRepository
import com.dcapp.creds_keeper.repository.creds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel(private val credRepository: CredRepository ) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            credRepository.fetchAllCreds()
        }
    }
    fun getCredLiveList() : LiveData<List<Cred>>{
        return credRepository.allCreds
//        return credRepository.fetchAllCreds()
    }

//    fun getCredLiveList(query : String?=null) : LiveData<List<Cred>>{
//        if(query==null){
//            return credRepository.getAllCreds()
//        }else{
//            return credRepository.searchAllCreds(query)
//        }
//    }

    fun insertCred(cred: Cred){
        viewModelScope.launch(Dispatchers.IO){
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
            credRepository.searchAllCreds(query)
        }
    }

    fun clearSearch(){
        viewModelScope.launch(Dispatchers.IO) {
            credRepository.clearSearch()
        }
    }

}

//
//class HomeViewModel(private val credRepository: CredRepository ) : ViewModel() {
//
//    fun getCredLiveList() : LiveData<List<Cred>>{
//        return credRepository.allCreds
//    }
//
//    fun addOrUpdateCred(cred: Cred){
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
//        credRepository.searchAllCreds(query)
//    }
//
//    fun clearSearch()= credRepository.clearSearch()
//
//}