package com.dcapp.creds_keeper.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dcapp.creds_keeper.db.CredDao
import com.dcapp.creds_keeper.models.Cred
import com.dcapp.creds_keeper.utils.Logger
import javax.inject.Inject

class CredRepository @Inject constructor(private val credDao : CredDao){
    private val _allCreds= MutableLiveData<List<Cred>>()
    private val _bookmarkedCreds = MutableLiveData<List<Cred>>()

    val allCreds : LiveData<List<Cred>>
        get() = _allCreds

    val bookmarkedCreds : LiveData<List<Cred>>
        get() = _bookmarkedCreds

    suspend fun fetchAllCreds(){
        _allCreds.postValue(credDao.getAllCreds())
    }

    suspend fun fetchBookmarkedCreds(){
        _bookmarkedCreds.postValue(credDao.getBookmarkedCreds())
    }

    private suspend fun refreshData(){
        fetchAllCreds()
        fetchBookmarkedCreds()
    }

    suspend fun insertCred(cred:Cred){
        credDao.insertCred(cred)
        refreshData()
    }

    suspend fun updateCred(cred:Cred){
        credDao.updateCred(cred)
        refreshData()
    }

    suspend fun deleteCred(cred:Cred){
        credDao.deleteCred(cred)
        refreshData()
    }

    suspend fun toggleBookmark(cred:Cred){
        try{
            cred.isBookmarked = !cred.isBookmarked
            updateCred(cred)
        }catch (e : Exception){
            Logger.error(e)
        }
    }

    suspend fun searchAllCreds(query : String){
        _allCreds.postValue(credDao.searchAllCreds(generatePattern(query)))
//        _allCreds.value = allCreds.value?.filter{ it.title.contains(query,true) }
    }

    suspend fun searchBookmarkedCreds(query : String){
        _bookmarkedCreds.postValue(credDao.searchBookmarkedCreds(generatePattern(query)))
    }

    suspend fun clearSearch() = refreshData()

    private fun generatePattern(query: String) : String{
        var pattern = ""
        query.trim().forEach { c->
            pattern+="%$c"
        }
        pattern+="%"
        Log.d("PATTERN ", pattern)
        return pattern
    }

}
