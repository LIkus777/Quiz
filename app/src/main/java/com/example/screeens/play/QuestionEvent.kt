package com.example.screeens.play

sealed class QuestionEvent {
    //todo сделай нормальный event, и переименуй. этот хуйня, по логике вещей ты должен обрабатывать event на:
    //todo нажатие правильного ответа, нажатие неправильного ответа, кнопки Next
    object FirstInitial: QuestionEvent()
    object CorrectAnswerClicked: QuestionEvent()
    object IncorrectAnswerClicked: QuestionEvent()
    object NextQuestionClicked: QuestionEvent()
    object TimeCountStart: QuestionEvent()
}
