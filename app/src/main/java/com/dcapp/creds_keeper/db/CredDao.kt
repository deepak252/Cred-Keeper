package com.dcapp.creds_keeper.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dcapp.creds_keeper.models.Cred

@Dao
interface CredDao {

    @Insert
    suspend fun insertCred(cred: Cred)

    @Query("SELECT * FROM cred")
    suspend fun getAllCreds() : List<Cred>

    @Query("SELECT * FROM cred WHERE id = :id")
    fun getCred(id : Int) : LiveData<Cred>?

    @Query("SELECT * FROM cred WHERE isBookmarked = 1")
    suspend fun getBookmarkedCreds() : List<Cred>

    @Delete
    suspend fun deleteCred(cred:Cred) : Int

    @Update
    suspend fun updateCred(cred: Cred)

    @Query("SELECT * FROM cred WHERE title LIKE :pattern")
    suspend fun searchAllCreds(pattern : String) : List<Cred>

    @Query("SELECT * FROM cred WHERE isBookmarked = 1 AND title LIKE :pattern")
    suspend fun searchBookmarkedCreds(pattern : String) : List<Cred>

}

//@Query("SELECT * FROM cred WHERE title LIKE :pattern")
//fun getAllCreds(pattern : String="") : LiveData<List<Cred>>
//
//@Query("SELECT * FROM cred WHERE id = :id")
//fun getCred(id : Int) : LiveData<Cred>?
//
//@Query("SELECT * FROM cred WHERE isBookmarked = 1 AND title LIKE :pattern")
//fun getBookmarkedCreds(pattern : String="") : LiveData<List<Cred>>