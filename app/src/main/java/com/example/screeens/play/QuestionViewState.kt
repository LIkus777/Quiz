package com.example.screeens.play

import com.example.data.Answers

sealed class QuestionViewState {
    //todo класс хуйня, надо переделать, и переименовать. у тебя есть всего 3 состояния экрана:
    //todo старт, процесс игры, и финиш. разбей состояния на отдельный класс ViewState (для всех 3)
    //object QuestionLoading: QuestionViewState()
    //object NextQuestionClicked: QuestionViewState()
    object Loading: QuestionViewState()
    object CorrectLight: QuestionViewState()
    object IncorrectLight: QuestionViewState()
    data class Display(
        val question: String,
        val answerList: List<Answers>
    ): QuestionViewState()
}
