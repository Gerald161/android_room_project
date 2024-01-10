package com.example.roomapplication.di

import android.content.Context
import androidx.room.Room
import com.example.roomapplication.data.PersonDao
import com.example.roomapplication.data.PersonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun getDatabase(
        @ApplicationContext context: Context
    ) : PersonDatabase {
        return Room.databaseBuilder(
            context,
            PersonDatabase::class.java,
            "person_database"
        ).build()
    }

    @Provides
    @Singleton
    fun getPersonDao(database: PersonDatabase): PersonDao{
        return database.personDao()
    }
}