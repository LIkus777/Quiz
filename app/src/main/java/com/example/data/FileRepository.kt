package com.example.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

data class Question(
    val question: String,
    val answers: List<Answers>
)

data class Answers(
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val correctAnswer: String
)

class FileRepository @Inject constructor() {

    //constructor(index: Int) : this(0) {}
    //constructor(index: Int) : this(this.index)
    var index: Int = 0
    var correctAnswers = 0
    var incorrectAnswers = 0
    private val typeToken = object : TypeToken<List<Question>>() {}.type
    private val json = """
        [
            {
                "question": "Вопрос1",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос2",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос3",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос4",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос5",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос6",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос7",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос8",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос9",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
            {
                "question": "Вопрос10",
                "answers": [{"answer1": "lolo", "answer2": "cringe", "answer3": "vkvkvk", "correctAnswer": "cringe"}]
            },
        ]
    """

    fun execute(): List<Question> = Gson().fromJson<List<Question>>(json, typeToken)

}