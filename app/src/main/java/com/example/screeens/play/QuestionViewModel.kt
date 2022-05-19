package com.example.screeens.play

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.EventHandler
import com.example.data.Answers
import com.example.data.FileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor (
    private val fileRepository: FileRepository
): ViewModel(), EventHandler<QuestionEvent> {
    //todo накидай лив даты, обработчиики событий
    //todo добавить состояние в QuestionViewState (связанное с JSON)

    private val _questionViewState = MutableLiveData<QuestionViewState>(QuestionViewState.Loading)
    val questionViewState: LiveData<QuestionViewState> = _questionViewState

    private var index = fileRepository.index
    private var correctAnswers = fileRepository.correctAnswers
    private var incorrectAnswers = fileRepository.incorrectAnswers

    override fun obtainEvent(event: QuestionEvent) {
        when (val currentState = _questionViewState.value) {
            is QuestionViewState.Display -> reduce(event, currentState)
            is QuestionViewState.Loading -> reduce(event, currentState)
        }
    }

    private fun reduce(event: QuestionEvent, currentState: QuestionViewState.Loading) {
        when (event) {
            is QuestionEvent.FirstInitial -> firstLoadQuestion()
        }
    }

    private fun reduce(event: QuestionEvent, currentState: QuestionViewState.Display) {
        when (event) {
            is QuestionEvent.CorrectAnswerClicked -> correctAnswer()
            is QuestionEvent.IncorrectAnswerClicked -> incorrectAnswer()
            is QuestionEvent.NextQuestionClicked -> loadQuestion()
            is QuestionEvent.TimeCountStart -> loadTime()
        }
    }

    private fun loadTime() {

        viewModelScope.launch {
            try {

                var time = 0L

                object : CountDownTimer(5000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        Log.i("TAG", "onTick: $millisUntilFinished")
                        time = millisUntilFinished/1000
                    }

                    override fun onFinish() {
                        _questionViewState.postValue(
                            QuestionViewState.Display(
                                time = time
                            )
                        )
                        cancel()
                    }

                }.start()



            } catch (e: Exception) {

            }
        }

    }

    private fun correctAnswer() {

        try {
            val listOfQuestionAndAnswers = fileRepository.execute()

            val question: String = listOfQuestionAndAnswers[index].question
            val answers: List<Answers> = listOfQuestionAndAnswers[index].answers

            Log.i("TAG", "correctAnswerLight: $index")

            _questionViewState.postValue(
                QuestionViewState.Display(
                    question = question,
                    answerList = answers,
                )
            )

            fileRepository.correctAnswers++

        } catch (e: Exception) {
            Log.i("TAG", "correctAnswer: error $e")
        }
    }

    private fun incorrectAnswer() {

        try {
            val listOfQuestionAndAnswers = fileRepository.execute()

            val question: String = listOfQuestionAndAnswers[index].question
            val answers: List<Answers> = listOfQuestionAndAnswers[index].answers

            Log.i("TAG", "incorrectAnswerLight: $index")

            _questionViewState.postValue(
                QuestionViewState.Display(
                    question = question,
                    answerList = answers,
                )
            )
            fileRepository.incorrectAnswers++

        } catch (e: Exception) {
            Log.i("TAG", "incorrectAnswer: error $e")
        }
    }

    private fun firstLoadQuestion() {

        Log.i("TAG", "firstLoadQuestion: called")

        viewModelScope.launch {

            try {

                fileRepository.correctAnswers = 0
                fileRepository.incorrectAnswers = 0

                val listOfQuestionAndAnswers = fileRepository.execute()

                val question: String = listOfQuestionAndAnswers[index].question
                val answers: List<Answers> = listOfQuestionAndAnswers[index].answers

                _questionViewState.postValue(
                    QuestionViewState.Display(
                        question = question,
                        answerList = answers
                    )
                )
            } catch (e: Exception) {
                Log.i("TAG", "loadQuestion: error $e")
            }
        }
    }

    private fun loadQuestion() {

        Log.i("TAG", "loadQuestion: called")

        viewModelScope.launch {

            try {

                index++

                if (index <= 8) {


                    val listOfQuestionAndAnswers = fileRepository.execute()

                    val question: String = listOfQuestionAndAnswers[index].question
                    val answers: List<Answers> = listOfQuestionAndAnswers[index].answers

                    _questionViewState.postValue(
                        QuestionViewState.Display(
                            question = question,
                            answerList = answers
                        )
                    )

                } else {

                    val listOfQuestionAndAnswers = fileRepository.execute()

                    val question: String = listOfQuestionAndAnswers[index].question
                    val answers: List<Answers> = listOfQuestionAndAnswers[index].answers

                    _questionViewState.postValue(
                        QuestionViewState.Display(
                            question = question,
                            answerList = answers,
                            isLastQuestion = true
                        )
                    )
                    return@launch
                }


            } catch (e: Exception) {
                Log.i("TAG", "loadQuestion: error $e")
            }

        }


    }

}