package com.dcapp.creds_keeper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var bottomNavIndex = MutableLiveData(0)
    private var isSearchEnabled = MutableLiveData(false)

    val bottomNavIndexLiveData : LiveData<Int>
        get()= bottomNavIndex

    val isSearchEnabledLiveData : LiveData<Boolean>
        get()= isSearchEnabled

    fun updateBottomNavIndex(index : Int){
        bottomNavIndex.value = index
    }

    fun toggleSearch(){
        isSearchEnabled.value = !isSearchEnabled.value!!
    }





}