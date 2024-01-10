package com.example.roomapplication.NavGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.roomapplication.MainViewModel
import com.example.roomapplication.NavGraph.Screens.DetailScreen
import com.example.roomapplication.NavGraph.Screens.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController){
    val myViewModel = hiltViewModel<MainViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController, myViewModel)
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.IntType
                    defaultValue = 1
                },
            ),
        ){
            DetailScreen(it.arguments?.getInt("id"), myViewModel)
        }
    }
}