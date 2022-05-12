package com.example

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screeens.navigation.NavigationTree
import com.example.screeens.play.QuestionViewModel
import com.example.screeens.start.StartScreen

@Composable
fun ApplicationScreen() {

    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = NavigationTree.Start.name) {
        composable(NavigationTree.Start.name) { StartScreen(navController) }
        composable(NavigationTree.Play.name) {
            val questionViewModel = hiltViewModel<QuestionViewModel>()
            QuestionScreen(navController, questionViewModel)
        }
        composable(NavigationTree.Finish.name) { FinishScreen() }
    }
}
