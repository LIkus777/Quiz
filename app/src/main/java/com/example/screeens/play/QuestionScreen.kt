package com.example

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.screeens.play.*

@Composable
fun QuestionScreen(
    navController: NavController,
    questionViewModel: QuestionViewModel
) {

    val viewState = questionViewModel.questionViewState.observeAsState()

    Surface() {
        Row {
            when (val state = viewState.value) {
                is QuestionViewState.CorrectLight -> CorrectLightView()
                is QuestionViewState.IncorrectLight -> IncorrectLightView()
                is QuestionViewState.Display -> DisplayView(
                    /*getQuestionText = { questionViewModel.obtainEvent(QuestionEvent.GetQuestionText) },
                    getAnswersList = { questionViewModel.obtainEvent(QuestionEvent.GetAnswersList) },*/
                    viewState = state
                )
            }

            Button(onClick = {
                questionViewModel.obtainEvent(event = QuestionEvent.NextQuestionClicked)
            }) {
                Text(text = "Next")
            }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        questionViewModel.obtainEvent(event = QuestionEvent.FirstInitial)
    })
}