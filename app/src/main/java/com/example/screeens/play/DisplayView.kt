package com.example.screeens.play

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun DisplayView(
    viewState: QuestionViewState.Display,
    correctAnswerClicked: () -> Unit,
    incorrectAnswerClicked: () -> Unit,
    nextQuestion: () -> Unit
) {

    Log.i("TAG", "DisplayView: $viewState")
    Log.i("TAG", "DisplayView: ${viewState.question}")

    val context = LocalContext.current

    Box() {
        LazyColumn {
            item {
                Text(
                    text = viewState.question,
                    modifier = Modifier.padding(10.dp)
                )
                viewState.answerList.forEach {
                    ClickableText(
                        text = AnnotatedString(viewState.answerList.component1().answer1),
                        modifier = Modifier.padding(10.dp),
                        onClick = {
                            if (viewState.answerList.component1().correctAnswer == viewState.answerList.component1().answer1) {
                                correctAnswerClicked.invoke()
                                Toast.makeText(context, "Correct!", Toast.LENGTH_LONG).show()
                                nextQuestion.invoke()
                            } else {
                                incorrectAnswerClicked.invoke()
                                Toast.makeText(context, "Incorrect :( The correct answer is: ${viewState.answerList.component1().correctAnswer}", Toast.LENGTH_LONG).show()
                                nextQuestion.invoke()
                            }
                        }
                    )
                    ClickableText(
                        text = AnnotatedString(viewState.answerList.component1().answer2),
                        modifier = Modifier.padding(10.dp),
                        onClick = {
                            if (viewState.answerList.component1().correctAnswer == viewState.answerList.component1().answer2) {
                                correctAnswerClicked.invoke()
                                Toast.makeText(context, "Correct!", Toast.LENGTH_LONG).show()
                                nextQuestion.invoke()
                            } else {
                                incorrectAnswerClicked.invoke()
                                Toast.makeText(context, "Incorrect :( The correct answer is: ${viewState.answerList.component1().correctAnswer}", Toast.LENGTH_LONG).show()
                                nextQuestion.invoke()
                            }
                        }
                    )
                    ClickableText(
                        text = AnnotatedString(viewState.answerList.component1().answer3),
                        modifier = Modifier.padding(10.dp),
                        onClick = {
                            if (viewState.answerList.component1().correctAnswer == viewState.answerList.component1().answer3) {
                                correctAnswerClicked.invoke()
                                Toast.makeText(context, "Correct!", Toast.LENGTH_LONG).show()
                                nextQuestion.invoke()
                            } else {
                                incorrectAnswerClicked.invoke()
                                Toast.makeText(context, "Incorrect :( The correct answer is: ${viewState.answerList.component1().correctAnswer}", Toast.LENGTH_LONG).show()
                                nextQuestion.invoke()
                            }
                        }
                    )
                }
            }
        }
    }
}