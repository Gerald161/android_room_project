package com.example.roomapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY id ASC")
    fun getAllPersons() : Flow<List<Person>>

    @Query("SELECT * FROM person WHERE id like :id")
    fun getPerson(id: Int) : Flow<Person>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: Person)
    
    @Query("DELETE FROM person")
    suspend fun deleteAllEntries()

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
}