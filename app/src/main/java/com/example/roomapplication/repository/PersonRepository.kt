package com.example.roomapplication.repository

import com.example.roomapplication.data.Person
import com.example.roomapplication.data.PersonDao
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val personDao : PersonDao
) {
    val allPersons = personDao.getAllPersons()

    suspend fun addPerson(person: Person){
        personDao.addPerson(person)
    }

    suspend fun deleteAllEntries(){
        personDao.deleteAllEntries()
    }
}