package com.example.screeens.play

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

    override fun obtainEvent(event: QuestionEvent) {
        when (val currentState = _questionViewState.value) {
            is QuestionViewState.Display -> reduce(event, currentState)
            is QuestionViewState.Loading -> reduce(event, currentState)
        }
    }

    private fun reduce(event: QuestionEvent, currentState: QuestionViewState.Loading) {
        when (event) {
            is QuestionEvent.FirstInitial -> loadQuestion()
        }
    }

    private fun reduce(event: QuestionEvent, currentState: QuestionViewState.Display) {
        when (event) {
            is QuestionEvent.CorrectAnswerClicked -> loadQuestion(correctAnswer = true)
            is QuestionEvent.InCorrectAnswerClicked -> loadQuestion(incorrectAnswer = true)
            is QuestionEvent.NextQuestionClicked -> loadQuestion()
        }
    }

    private fun getQuestionText(): String = fileRepository.execute().component1().question
    private fun getAnswersList(): List<Answers> = fileRepository.execute().component1().answers

    private fun loadQuestion(correctAnswer: Boolean = false, incorrectAnswer: Boolean = false) {

        //Log.i("TAG", "loadQuestion: $")
        
        if (correctAnswer)
            _questionViewState.postValue(QuestionViewState.CorrectLight)
        else if (incorrectAnswer)
            _questionViewState.postValue(QuestionViewState.IncorrectLight)

        viewModelScope.launch {

            try {
                val listOfQuestionAndAnswers = fileRepository.execute()

                val question: String = listOfQuestionAndAnswers[index].question
                val answers: List<Answers> = listOfQuestionAndAnswers[index].answers

                _questionViewState.postValue(
                    QuestionViewState.Display(
                        question = question,
                        answerList = answers
                    )
                )

                index++
                
            } catch (e: Exception) {
                Log.i("TAG", "loadQuestion: error $e")
            }

        }


    }

}