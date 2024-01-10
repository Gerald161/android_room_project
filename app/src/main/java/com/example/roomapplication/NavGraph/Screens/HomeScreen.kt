package com.example.roomapplication.NavGraph.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.roomapplication.MainViewModel
import com.example.roomapplication.data.Person
import com.example.roomapplication.NavGraph.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    myViewModel: MainViewModel = hiltViewModel()
){
    val context = LocalContext.current

    val persons by myViewModel.allPersons.collectAsState(initial = emptyList())

    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if(persons.isNotEmpty()){
            for (person in persons){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(route = Screen.DetailScreen.passId(person.id))
                        }
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = person.name,
                    )
                    IconButton(onClick = {
                        myViewModel.deletePerson(person)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                        )
                    }
                }
            }
        }else{
            Text("Nothing added yet")
        }

        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Enter Name")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    if(name.replace("\\s".toRegex(), "") != ""){
                        myViewModel.addPerson(Person(0, name))

                        name = ""

                        Toast.makeText(
                            context,
                            "Successfully added person",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            context,
                            "Please do not leave it empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        )

        Button(onClick = {
            if(name.replace("\\s".toRegex(), "") != ""){
                myViewModel.addPerson(Person(0, name))

                name = ""

                Toast.makeText(
                    context,
                    "Successfully added person",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    context,
                    "Please do not leave it empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }) {
            Text("Add")
        }

        Button(onClick = {
            myViewModel.deleteAllEntries()

            Toast.makeText(
                context,
                "Blown to bits, reduced to atoms",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Text("Delete all")
        }
    }
}