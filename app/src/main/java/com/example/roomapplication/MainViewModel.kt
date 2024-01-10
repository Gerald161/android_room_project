package com.example.roomapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapplication.data.Person
import com.example.roomapplication.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val personRepository: PersonRepository
): ViewModel() {
    val allPersons = personRepository.allPersons

    fun addPerson(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.addPerson(person)
        }
    }

    fun deleteAllEntries(){
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.deleteAllEntries()
        }
    }
}