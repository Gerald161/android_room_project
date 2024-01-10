package com.example.roomapplication.NavGraph

sealed class Screen(val route: String){
    object HomeScreen: Screen(route = "home_screen")
    object DetailScreen: Screen(route = "detail_screen")
}