package com.dcapp.creds_keeper.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cred")
data class Cred(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val uid : String,
    val pwd : String,
    @ColumnInfo(defaultValue = "")
    val desc : String,
    @ColumnInfo(defaultValue = "0")
    var isBookmarked : Boolean
)
