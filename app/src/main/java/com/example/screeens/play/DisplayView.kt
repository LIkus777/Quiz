package com.example.screeens.play

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisplayView(
    viewState: QuestionViewState.Display
) {
    Box(modifier = Modifier.padding(20.dp)) {
        LazyColumn {
            item {
                Text(
                    text = "${viewState.question}",
                    modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                )
                viewState.answerList.forEach {
                    Text(text = viewState.answerList.component1().answer1)
                    Text(text = viewState.answerList.component1().answer2)
                    Text(text = viewState.answerList.component1().answer3)
                }
            }
        }
    }
}