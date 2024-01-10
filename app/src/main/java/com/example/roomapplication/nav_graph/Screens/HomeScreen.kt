package com.example.roomapplication.nav_graph.Screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.example.roomapplication.nav_graph.Screen

@Composable
fun HomeScreen(navController: NavController){
    val context = LocalContext.current

    val myViewModel = hiltViewModel<MainViewModel>()

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
                Text(
                    text = person.name,
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screen.DetailScreen.route)
                    }
                )
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
                    if(name != ""){
                        myViewModel.addPerson(Person(0, name))

                        name = ""

                        Toast.makeText(
                            context,
                            "Successfully added person",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        )

        Button(onClick = {
            if(name != ""){
                myViewModel.addPerson(Person(0, name))

                name = ""

                Toast.makeText(
                    context,
                    "Successfully added person",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }) {
            Text("Add")
        }
    }
}