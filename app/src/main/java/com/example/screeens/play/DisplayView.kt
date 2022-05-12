package com.example.screeens.play

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DisplayView(
/*    getQuestionText: () -> Unit,
    getAnswersList: () -> Unit,*/
    viewState: QuestionViewState.Display
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "${viewState.question}"
        )
        LazyColumn {
            item {
                viewState.answerList.forEach {
                    Text(text = viewState.answerList.component1().answer1)
                    Text(text = viewState.answerList.component1().answer2)
                    Text(text = viewState.answerList.component1().answer3)
                }
            }
        }
    }
}