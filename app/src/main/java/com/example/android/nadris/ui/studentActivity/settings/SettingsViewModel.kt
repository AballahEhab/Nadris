package com.example.android.nadris.ui.studentActivity.settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.network.firebase.dtos.Grade
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SettingsViewModel  @Inject constructor(val repository: Repository): ViewModel(){
    val TAG = "SettingsViewModel"

    val gradesList:MutableLiveData<List<Grade>> = MutableLiveData()

    fun getGrades(){
        gradesList.value = listOf(
            Grade(name_ar = "le1"),
            Grade(name_ar = "le2"),
            Grade(name_ar = "le3"),
            Grade(name_ar = "le4"),
            Grade(name_ar = "le5"),
            Grade(name_ar = "le6"),
            Grade(name_ar = "le7"),
            Grade(name_ar = "le8")
        )
    }

    var gradesListstest: MutableList<Grade> = mutableListOf()

    fun getGradestest(){
        var que:ArrayList<Grade> = arrayListOf()
        var Grade1 = Grade("1","1" ,"10","scence","0","prep first")
        var Grade2 = Grade("2","1" ,"10","scence","0","prep second")
        var Grade3 = Grade("3","1" ,"10","scence","0","prep third")
        var Grade4 = Grade("4","1" ,"10","scence","0","secondray first")
        var Grade5 = Grade("4","1" ,"10","scence","0","secondray first")
        var Grade6 = Grade("4","1" ,"10","scence","0","secondray first")
        que.add(Grade1)
        que.add(Grade2)
        que.add(Grade3)
        que.add(Grade4)
        que.add(Grade5)
        que.add(Grade6)

        gradesListstest = que

        }





    fun setGrade(id: String?) {
        // please send this id to firebase
        val grade = gradesList.value?.find { it.id == id}
        Log.v(TAG,"grade changed to be ${grade?.name_ar} ")

    }
}