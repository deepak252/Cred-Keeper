package com.dcapp.creds_keeper.repository

import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dcapp.creds_keeper.model.Cred


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

class CredRepository : ViewModel() {
    private val credList = creds.toMutableList()
//    private val credList = mutableListOf<Cred>().apply { addAll(creds) }

    private val allCredsLiveData = MutableLiveData<List<Cred>>(credList)
    private val bookmarkedCredsLiveData = MutableLiveData<List<Cred>>(credList.filter { it.isBookmarked })

    val allCreds : LiveData<List<Cred>>
        get() = allCredsLiveData

    val bookmarkedCreds : LiveData<List<Cred>>
        get() = bookmarkedCredsLiveData

    private fun setData(){
        allCredsLiveData.value = credList
        bookmarkedCredsLiveData.value = credList.filter { it.isBookmarked }
    }

    fun addOrUpdateCred(cred : Cred){
        val i = credList.indexOfFirst { it.id==cred.id }
        if(i==-1){  // Add
            credList.add(cred)
        }else{  // Update
            credList[i] = cred
        }
        setData()
    }

    fun deleteCred(credId : Int){
        val i = credList.indexOfFirst { it.id==credId }
        if(i!=-1){
            credList.removeAll { it.id==credId }
            setData()
        }
    }

    fun toggleBookmark(credId : Int){
        val i = credList.indexOfFirst { it.id==credId }
        if(i!=-1){
            credList[i].isBookmarked = !credList[i].isBookmarked
            Log.d("MyTag", credList[i].isBookmarked.toString())
        }
        setData()
    }

    companion object{
        private var INSTANCE : CredRepository?=null
        fun getInstance() : CredRepository{
            if(INSTANCE==null){
                INSTANCE = CredRepository()
            }
            return INSTANCE!!
        }
    }

}




//package com.dcapp.creds_keeper.repository
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.dcapp.creds_keeper.model.Cred
//
//
//var creds = arrayListOf<Cred>(
//    Cred(0,"Coding Ninja", "deepak@gmail.com", "1234","",false),
//    Cred(1,"Codeforces", "deepak@gmail.com", "981234","",true),
//    Cred(2,"Gmail", "deepak@gmail.com", "SD1234","",false),
//    Cred(3,"Google", "deepak@gmail.com", "##1234","",true),
//    Cred(4,"Facebook", "deepak@gmail.com", "GHD1234","",true),
//    Cred(5,"Twitter", "deepak@gmail.com", "0931234","",false),
//    Cred(6,"Instagram", "deepak@gmail.com", "ERER013234","",false),
//    Cred(7,"Flipkart", "deepak@gmail.com", "FLIP@#$@","",false),
//)
//
//class CredRepository : ViewModel() {
////    private val credList = creds.toMutableList()
//    private val credList = mutableListOf<Cred>().apply { addAll(creds) }
//    private val credMutableList = MutableLiveData<ArrayList<Cred>>(creds)
//
//    val credLiveList : LiveData<ArrayList<Cred>>
//        get() = credMutableList
//
//    fun insertCred(cred : Cred){
//        credMutableList.value?.add(cred)
//        updateData()
//    }
//
//    fun getBookmarks() : LiveData<ArrayList<Cred>>{
////        val bookmarks = credLiveList
//        val bookmarks = MutableLiveData<ArrayList<Cred>>(arrayListOf())
//        for(cred in credLiveList.value!!){
//            if(cred.bookmarked){
//                bookmarks.value?.add((cred))
//            }
//        }
//        return bookmarks
//
//    }
//
//    private fun updateData(){
//        credMutableList.value = credLiveList.value
//    }
//
//    fun toggleBookmark(credId : Int){
//        val i = credMutableList.value?.indexOfFirst { cred-> cred.id==credId }
//        if(i!=null && i!=-1){
//            val isBookmarked = credMutableList.value!![i].bookmarked
//            credMutableList.value!![i].bookmarked = !isBookmarked
//            Log.d("MyTag", isBookmarked.toString())
//        }
//        updateData()
//
//    }
//
//    companion object{
//        private var INSTANCE : CredRepository?=null
//        fun getInstance() : CredRepository{
//            if(INSTANCE==null){
//                INSTANCE = CredRepository()
//            }
//            return INSTANCE!!
//        }
//
//    }
//
//}