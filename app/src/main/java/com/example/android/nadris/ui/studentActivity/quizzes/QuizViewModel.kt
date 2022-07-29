package com.example.android.nadris.ui.studentActivity.quizzes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.network.firebase.dtos.QuestionData
import com.example.android.nadris.network.firebase.dtos.QuizData
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(val repository: Repository):ViewModel() {


     var score:Int=0
     var currentPosition:Int=1
     var questionList:MutableList<QuestionData>? = null
     var questionsResult:MutableLiveData<Result<QuizData?>> = MutableLiveData()
     var selectedOption: MutableList<Int> = mutableListOf()
     var wrongQuestion : MutableList<Int> = mutableListOf()


     fun getQuizWithId(quizId:String){
          viewModelScope.launch(Dispatchers.IO){
              repository.getQuizQuestionsWithQuizId(quizId).collect{
                   questionsResult.postValue(it)
              }
          }
     }

     fun delet() {
          this.onCleared()
     }

}