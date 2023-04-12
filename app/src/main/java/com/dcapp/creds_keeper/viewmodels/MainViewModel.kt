package com.dcapp.creds_keeper.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
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