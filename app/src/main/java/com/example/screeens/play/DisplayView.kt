package com.example.screeens.play

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.screeens.navigation.NavigationTree
import com.example.ui.theme.LIGHT_PRIMARY_COLOR
import com.example.ui.theme.PRIMARY_TEXT
import com.example.ui.theme.SECONDARY_TEXT
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun DisplayView(
    navController: NavController,
    state: QuestionViewState.Display,
    correctAnswerClicked: () -> Unit,
    incorrectAnswerClicked: () -> Unit,
    nextQuestion: () -> Unit,
) {

    val context = LocalContext.current

    val timeText = remember { mutableStateOf("") }

    var ticks by remember { mutableStateOf(10) }
    LaunchedEffect(key1 = nextQuestion) {
        ticks = 10
        do {
            delay(1.seconds)
            ticks--
        } while(ticks >= 1)
        incorrectAnswerClicked.invoke()
        nextQuestion.invoke()
    }

    @Composable
    fun showQuestion() {
        Text(
            text = state.question,
            color = PRIMARY_TEXT,
            modifier = Modifier.padding(10.dp)
        )
    }

    @Composable
    fun showAnswer() {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Time: ${ticks}"
        )
        state.answerList.forEach {
            ClickableText(
                text = AnnotatedString(state.answerList.component1().answer1),
                style = TextStyle(color = SECONDARY_TEXT),
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (state.answerList.component1().correctAnswer == state.answerList.component1().answer1) {
                        correctAnswerClicked.invoke()
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                        nextQuestion.invoke()
                    } else {
                        incorrectAnswerClicked.invoke()
                        Toast.makeText(context, "Incorrect :( The correct answer is: ${state.answerList.component1().correctAnswer}", Toast.LENGTH_SHORT).show()
                        nextQuestion.invoke()
                    }
                }
            )
            ClickableText(
                text = AnnotatedString(state.answerList.component1().answer2),
                style = TextStyle(color = SECONDARY_TEXT),
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (state.answerList.component1().correctAnswer == state.answerList.component1().answer2) {
                        correctAnswerClicked.invoke()
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                        nextQuestion.invoke()
                    } else {
                        incorrectAnswerClicked.invoke()
                        Toast.makeText(context, "Incorrect :( The correct answer is: ${state.answerList.component1().correctAnswer}", Toast.LENGTH_SHORT).show()
                        nextQuestion.invoke()
                    }
                }
            )
            ClickableText(
                text = AnnotatedString(state.answerList.component1().answer3),
                style = TextStyle(color = SECONDARY_TEXT),
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (state.answerList.component1().correctAnswer == state.answerList.component1().answer3) {
                        correctAnswerClicked.invoke()
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                        nextQuestion.invoke()
                    } else {
                        incorrectAnswerClicked.invoke()
                        Toast.makeText(context, "Incorrect :( The correct answer is: ${state.answerList.component1().correctAnswer}", Toast.LENGTH_SHORT).show()
                        nextQuestion.invoke()
                    }
                }
            )
        }
    }

    @Composable
    fun showLastAnswer() {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Time: ${ticks}")
        state.answerList.forEach {
            ClickableText(
                text = AnnotatedString(state.answerList.component1().answer1),
                style = TextStyle(color = SECONDARY_TEXT),
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (state.answerList.component1().correctAnswer == state.answerList.component1().answer1) {
                        correctAnswerClicked.invoke()
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                        navController.navigate(NavigationTree.Finish.name)
                    } else {
                        incorrectAnswerClicked.invoke()
                        Toast.makeText(context, "Incorrect :( The correct answer is: ${state.answerList.component1().correctAnswer}", Toast.LENGTH_SHORT).show()
                        navController.navigate(NavigationTree.Finish.name)
                    }
                }
            )
            ClickableText(
                text = AnnotatedString(state.answerList.component1().answer2),
                style = TextStyle(color = SECONDARY_TEXT),
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (state.answerList.component1().correctAnswer == state.answerList.component1().answer2) {
                        correctAnswerClicked.invoke()
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                        navController.navigate(NavigationTree.Finish.name)
                    } else {
                        incorrectAnswerClicked.invoke()
                        Toast.makeText(context, "Incorrect :( The correct answer is: ${state.answerList.component1().correctAnswer}", Toast.LENGTH_SHORT).show()
                        navController.navigate(NavigationTree.Finish.name)
                    }
                }
            )
            ClickableText(
                text = AnnotatedString(state.answerList.component1().answer3),
                style = TextStyle(color = SECONDARY_TEXT),
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (state.answerList.component1().correctAnswer == state.answerList.component1().answer3) {
                        correctAnswerClicked.invoke()
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                        nextQuestion.invoke()
                    } else {
                        incorrectAnswerClicked.invoke()
                        Toast.makeText(context, "Incorrect :( The correct answer is: ${state.answerList.component1().correctAnswer}", Toast.LENGTH_SHORT).show()
                        navController.navigate(NavigationTree.Finish.name)
                    }
                }
            )
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = LIGHT_PRIMARY_COLOR)
            .fillMaxSize()
    ){
        LazyColumn {
            if (state.isLastQuestion) {
                item {
                    showQuestion()
                    showLastAnswer()
                }

            } else {
                item {
                    showQuestion()
                    showAnswer()
                }
            }
        }
    }
}