package com.dcapp.creds_keeper.di

import android.content.Context
import androidx.room.Room
import com.dcapp.creds_keeper.db.CredDao
import com.dcapp.creds_keeper.db.CredDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCredDB(@ApplicationContext context : Context) : CredDatabase{
        return Room
            .databaseBuilder(
                context.applicationContext,
                CredDatabase:: class.java,
                "credDB"
            ).build()
    }

    @Provides
    fun provideUserDao(credDB: CredDatabase): CredDao {
        return credDB.credDao()
    }
}