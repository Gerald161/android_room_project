package com.example.roomapplication.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.roomapplication.nav_graph.Screens.DetailScreen
import com.example.roomapplication.nav_graph.Screens.HomeScreen

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