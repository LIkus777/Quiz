package com.example.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

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

@Singleton
class FileRepository @Inject constructor() {

    var index: Int = 0
    var correctAnswers = 0
    var incorrectAnswers = 0
    private val typeToken = object : TypeToken<List<Question>>() {}.type
    private val json = """
        [
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_1",
                "answers": [{"answer1": "ПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_2",
                "answers": [{"answer1": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "ПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_3",
                "answers": [{"answer1": "ПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_4",
                "answers": [{"answer1": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "ПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_5",
                "answers": [{"answer1": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "ПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_6",
                "answers": [{"answer1": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "ПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_7",
                "answers": [{"answer1": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "ПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_8",
                "answers": [{"answer1": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "ПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_9",
                "answers": [{"answer1": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "ПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
            {
                "question": "ЗАМЕНИТЬ_ВОПРОС_10",
                "answers": [{"answer1": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer2": "НЕПРАВИЛЬНЫЙ_ОТВЕТ", "answer3": "ПРАВИЛЬНЫЙ_ОТВЕТ", "correctAnswer": "ПРАВИЛЬНЫЙ_ОТВЕТ"}]
            },
        ]
    """

    fun execute(): List<Question> = Gson().fromJson<List<Question>>(json, typeToken)

}