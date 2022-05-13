package com.example

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.screeens.play.*

@Composable
fun QuestionScreen(
    navController: NavController,
    questionViewModel: QuestionViewModel
) {

    val viewState = questionViewModel.questionViewState.observeAsState()

    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val state = viewState.value) {
                QuestionViewState.CorrectLight -> CorrectLightView()
                QuestionViewState.IncorrectLight -> IncorrectLightView()
                is QuestionViewState.Display -> DisplayView(
                    viewState = state,
                    correctAnswerClicked = { questionViewModel.obtainEvent(QuestionEvent.CorrectAnswerClicked) },
                    incorrectAnswerClicked = { questionViewModel.obtainEvent(QuestionEvent.IncorrectAnswerClicked) },
                    nextQuestion = { questionViewModel.obtainEvent(event = QuestionEvent.NextQuestionClicked) }
                )
            }

            /*Button(onClick = {
                questionViewModel.obtainEvent(event = QuestionEvent.NextQuestionClicked)
            }) {
                Text(text = "Next")
            }*/
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        questionViewModel.obtainEvent(event = QuestionEvent.FirstInitial)
    })
}