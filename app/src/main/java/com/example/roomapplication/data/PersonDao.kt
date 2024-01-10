package com.example.roomapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY id ASC")
    fun getAllPersons() : Flow<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: Person)
    
    @Query("DELETE FROM person")
    suspend fun deleteAllEntries()
}