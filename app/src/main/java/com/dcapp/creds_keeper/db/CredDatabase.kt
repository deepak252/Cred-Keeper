package com.dcapp.creds_keeper.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dcapp.creds_keeper.models.Cred

@Database(entities = [Cred::class], version = 1)
abstract class CredDatabase : RoomDatabase(){
    abstract fun credDao() : CredDao
}