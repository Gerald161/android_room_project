package com.example.roomapplication.NavGraph.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomapplication.MainViewModel
import com.example.roomapplication.data.Person

@Composable
fun DetailScreen(
    id: Int ?,
    myViewModel: MainViewModel = hiltViewModel()
){
//    val person by myViewModel.getPerson(id!!).collectAsState(initial = Person(0,""))

    var name by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        myViewModel.getPerson(id!!).collect{
            name = it.name
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Text(person.name)

        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Update Name")
            },
        )

        Button(onClick = {
            if(name.replace("\\s".toRegex(), "") != ""){
                val newPerson = Person(id!!, name)

                myViewModel.updatePerson(newPerson)

                Toast.makeText(
                    context,
                    "Successfully updated person",
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
            Text("Update")
        }
    }
}