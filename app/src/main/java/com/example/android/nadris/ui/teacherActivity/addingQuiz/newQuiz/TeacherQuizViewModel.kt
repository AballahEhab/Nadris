package com.example.android.nadris.ui.teacherActivity.addingQuiz.newQuiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.ui.teacherActivity.addingQuiz.QuizData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeacherQuizViewModel @Inject constructor() : ViewModel() {
    val list = MutableLiveData<MutableList<QuizData>>(mutableListOf())
    val listChanged = MutableLiveData(false)

    init {
        addQuestion()
    }

    fun addQuestion(){
        list.value.let {
            val id = it?.size?.plus(1)
            it!!.add(QuizData(id!!,"",answer = mutableListOf("", "", "", ""), mutableListOf(),""))
        }
        listChanged.value = !listChanged.value!!
    }
}