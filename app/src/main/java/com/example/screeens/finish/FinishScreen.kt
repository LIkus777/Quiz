package com.example

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.screeens.finish.DisplayView
import com.example.screeens.finish.FinishEvent
import com.example.screeens.finish.FinishViewModel
import com.example.screeens.finish.FinishViewState
import com.example.screeens.navigation.NavigationTree
import com.example.ui.theme.ACCENT_COLOR
import com.example.ui.theme.LIGHT_PRIMARY_COLOR

@Composable
fun FinishScreen(
    navController: NavController,
    finishViewModel: FinishViewModel
) {

    val viewState = finishViewModel.finishViewState.observeAsState()

    /*Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = LIGHT_PRIMARY_COLOR)
            .fillMaxSize()
    ) {
        Column() {
            Text(text = "Finish")
        }
    }*/

    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LIGHT_PRIMARY_COLOR),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val state = viewState.value) {
                is FinishViewState.Display -> DisplayView(state = state)
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ACCENT_COLOR,
                    contentColor = LIGHT_PRIMARY_COLOR
                ),
                onClick = {
                navController.navigate(NavigationTree.Start.name)
            }) {
                Text(text = "Main menu")
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ACCENT_COLOR,
                    contentColor = LIGHT_PRIMARY_COLOR
                ),
                onClick = {
                navController.navigate(NavigationTree.Play.name)
            }) {
                Text(text = "Play again")
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        finishViewModel.obtainEvent(FinishEvent.ShowAnswersCount)
    }

}


