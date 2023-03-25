package com.dcapp.creds_keeper.model

data class Cred(
    val id : Int,
    val title : String,
    val uid : String,
    val pwd : String,
    val desc : String,
    var isBookmarked : Boolean = false
)
