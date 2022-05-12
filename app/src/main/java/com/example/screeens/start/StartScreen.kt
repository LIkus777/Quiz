package com.example.screeens.start

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.screeens.navigation.NavigationTree

@Composable
fun StartScreen(navController: NavController) {
    LaunchedEffect(key1 = Unit, block = {
        navController.navigate(NavigationTree.Play.name)
    })
}