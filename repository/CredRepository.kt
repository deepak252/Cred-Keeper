package com.dcapp.creds_keeper.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dcapp.creds_keeper.db.CredDao
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.utils.Logger


var creds = arrayListOf<Cred>(
    Cred(0,"Coding Ninja", "deepak@gmail.com", "1234","",false),
    Cred(1,"Codeforces", "deepak@gmail.com", "981234","",true),
    Cred(2,"Gmail", "deepak@gmail.com", "SD1234","",false),
    Cred(3,"Google", "deepak@gmail.com", "##1234","",true),
    Cred(4,"Facebook", "deepak@gmail.com", "GHD1234","",true),
    Cred(5,"Twitter", "deepak@gmail.com", "0931234","",false),
    Cred(6,"Instagram", "deepak@gmail.com", "ERER013234","",false),
    Cred(7,"Flipkart", "deepak@gmail.com", "FLIP@#$@","",false),
)


class CredRepository(private val credDao : CredDao){
    private val _allCreds= MutableLiveData<List<Cred>>()
    private val _bookmarkedCreds = MutableLiveData<List<Cred>>()

    val allCreds : LiveData<List<Cred>>
        get() = _allCreds

    val bookmarkedCreds : LiveData<List<Cred>>
        get() = _bookmarkedCreds

    suspend fun fetchAllCreds(){
        _allCreds.postValue(credDao.getAllCreds())
    }
//    fun fetchAllCreds():LiveData<List<Cred>>{
//        return credDao.getAllCreds()
//    }

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
            refreshData()
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


//    fun toggleBookmark(credId : Int){
//        val i = credList.indexOfFirst { it.id==credId }
//        if(i!=-1){
//            credList[i].isBookmarked = !credList[i].isBookmarked
//        }
//        setData()
//    }

//    fun searchAllCreds(query : String){
//        allCredsLiveData.value = credList.filter { it.title.contains(query,true) }
//    }
//
//    fun searchBookmarkedCreds(query : String){
//        bookmarkedCredsLiveData.value = credList.filter { it.title.contains(query,true)&& it.isBookmarked }
//    }

//    fun clearSearch() = setData()

}



//
//class CredRepository(private val credDao : CredDao){
//    private val credList = creds.toMutableList()
////    private val credList = mutableListOf<Cred>().apply { addAll(creds) }
//
//    private val allCredsLiveData = MutableLiveData<List<Cred>>(credList)
//    private val bookmarkedCredsLiveData = MutableLiveData<List<Cred>>(credList.filter { it.isBookmarked })
//
//    val allCreds : LiveData<List<Cred>>
//        get() = allCredsLiveData
//
//    val bookmarkedCreds : LiveData<List<Cred>>
//        get() = bookmarkedCredsLiveData
//
//    private fun setData(){
//        allCredsLiveData.value = credList
//        bookmarkedCredsLiveData.value = credList.filter { it.isBookmarked }
//    }
//
//    fun addOrUpdateCred(cred : Cred){
//        val i = credList.indexOfFirst { it.id==cred.id }
//        if(i==-1){  // Add
//            credList.add(cred)
//        }else{  // Update
//            credList[i] = cred
//        }
//        setData()
//    }
//
//    fun deleteCred(credId : Int){
//        val i = credList.indexOfFirst { it.id==credId }
//        if(i!=-1){
//            credList.removeAll { it.id==credId }
//            setData()
//        }
//    }
//
//    fun toggleBookmark(credId : Int){
//        val i = credList.indexOfFirst { it.id==credId }
//        if(i!=-1){
//            credList[i].isBookmarked = !credList[i].isBookmarked
//        }
//        setData()
//    }
//
//    fun searchAllCreds(query : String){
//        allCredsLiveData.value = credList.filter { it.title.contains(query,true) }
//    }
//
//    fun searchBookmarkedCreds(query : String){
//        bookmarkedCredsLiveData.value = credList.filter { it.title.contains(query,true)&& it.isBookmarked }
//    }
//
//    fun clearSearch() = setData()
//
////    companion object{
////        private var INSTANCE : CredRepository?=null
////        fun getInstance() : CredRepository{
////            if(INSTANCE==null){
////                INSTANCE = CredRepository()
////            }
////            return INSTANCE!!
////        }
////    }
//
//}
