package com.example.roomapplication.repository

import com.example.roomapplication.data.Person
import com.example.roomapplication.data.PersonDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val personDao : PersonDao
) {
    val allPersons = personDao.getAllPersons()

    fun getPerson(id: Int): Flow<Person> {
        return personDao.getPerson(id)
    }

    suspend fun addPerson(person: Person){
        personDao.addPerson(person)
    }

    suspend fun deleteAllEntries(){
        personDao.deleteAllEntries()
    }

    suspend fun deletePerson(person: Person){
        personDao.deletePerson(person)
    }

    suspend fun updatePerson(person: Person){
        personDao.updatePerson(person)
    }
}