package com.dcapp.creds_keeper.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dcapp.creds_keeper.models.Cred

@Database(entities = [Cred::class], version = 1)
abstract class CredDatabase : RoomDatabase(){
    abstract fun credDao() : CredDao

    companion object{
        @Volatile
        private var INSTANCE : CredDatabase?=null

        fun getDatabase(context : Context):CredDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room
                        .databaseBuilder(
                            context.applicationContext,
                            CredDatabase:: class.java,
                            "credDB"
                        )
//                        .createFromAsset("cred.db")
                        .build()

                }
            }
            return INSTANCE!!
        }
    }

}