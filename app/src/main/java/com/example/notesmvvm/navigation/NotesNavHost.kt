package com.example.notesmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesmvvm.screens.Add
import com.example.notesmvvm.screens.Main
import com.example.notesmvvm.screens.Note
import com.example.notesmvvm.screens.Start


sealed class NavRoute(val route: String) {
    data object Start: NavRoute("start_screen")
    data object Main: NavRoute("main_screen")
    data object Add: NavRoute("add_screen")
    data object Note: NavRoute("note_screen")
}


@Composable
fun NotesNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { Start(navController = navController) }
        composable(NavRoute.Main.route) { Main(navController = navController) }
        composable(NavRoute.Add.route) { Add(navController = navController) }
        composable(NavRoute.Note.route) { Note(navController = navController) }
    }
}
