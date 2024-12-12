package com.example.notesmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesmvvm.MainViewModel
import com.example.notesmvvm.screens.AddScreen
import com.example.notesmvvm.screens.MainScreen
import com.example.notesmvvm.screens.NoteScreen
import com.example.notesmvvm.screens.StartScreen


sealed class NavRoute(val route: String) {
    data object Start: NavRoute("start_screen")
    data object Main: NavRoute("main_screen")
    data object Add: NavRoute("add_screen")
    data object Note: NavRoute("note_screen")
}


@Composable
fun NotesNavHost(mViewModel: ViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = mViewModel as MainViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = mViewModel as MainViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = mViewModel as MainViewModel) }
        composable(NavRoute.Note.route) { NoteScreen(navController = navController, viewModel = mViewModel as MainViewModel) }
    }
}
