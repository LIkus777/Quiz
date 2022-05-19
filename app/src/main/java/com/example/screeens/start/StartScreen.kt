package com.example.screeens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quiz.R
import com.example.screeens.navigation.NavigationTree
import com.example.ui.theme.*
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun StartScreen(navController: NavController) {

    val name = LocalContext.current.resources.getString(R.string.app_name)
    val openDialog = remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = LIGHT_PRIMARY_COLOR)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$name",
                modifier = Modifier.padding(20.dp),
                color = TEXT_OR_ICONS
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ACCENT_COLOR,
                    contentColor = LIGHT_PRIMARY_COLOR
                ),
                onClick = {
                    navController.navigate(NavigationTree.Play.name)
            }) {
                Text(text = "Play")
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ACCENT_COLOR,
                    contentColor = LIGHT_PRIMARY_COLOR
                ),
                onClick = {
                    openDialog.value = true
            }) {
                Text(text = "Exit")
            }

            if (openDialog.value) {
                AlertDialog(
                    backgroundColor = PRIMARY_COLOR,
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Do you really want to get out?")
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = ACCENT_COLOR,
                                contentColor = LIGHT_PRIMARY_COLOR
                            ),
                            onClick = {
                                System.exit(0)
                            }
                        ) {
                            Text(text = "Yes")
                        }
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = ACCENT_COLOR,
                                contentColor = LIGHT_PRIMARY_COLOR
                            ),
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text(text = "No")
                        }
                    }
                )
            }

        }
    }
    
    LaunchedEffect(key1 = Unit, block = {

    })
}