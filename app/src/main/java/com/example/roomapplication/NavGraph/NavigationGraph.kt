package com.example.roomapplication.NavGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.roomapplication.NavGraph.Screens.DetailScreen
import com.example.roomapplication.NavGraph.Screens.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(
            route = Screen.DetailScreen.route,
        ){
            DetailScreen(navController)
        }
    }
}