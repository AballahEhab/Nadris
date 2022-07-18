package com.example.android.nadris.ui.teacherActivity.addingNewCourse

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AddNewSubjectViewModel @Inject constructor(
    val repository: Repository,
    @ApplicationContext val context: Context,
) : ViewModel() {


//    var subjects: MutableLiveData<List<SubjectDTO>> = MutableLiveData<List<SubjectDTO>>()
//    var grades: MutableLiveData<List<GradeDTO>> = MutableLiveData<List<GradeDTO>>()
    var selectedSubject: MutableLiveData<String> = MutableLiveData<String>()

    var gradeId = 1L
    var selectedTerm: MutableLiveData<String> = MutableLiveData<String>()
    var termList: ArrayList<String> = ArrayList()
    private var selectedGrade: MutableLiveData<String> = MutableLiveData<String>()
    fun setSelectedGrade(grade: String) {
        selectedGrade.value = grade
        getSubjects()
    }

    fun getSelectedGrade() = selectedGrade.value


    fun addSubject() {
//        val termId = termList.indexOf(selectedTerm.value) + 1
//        val token = NadrisApplication.currentUserLocalData!!.Token
//        val subjectId = subjects.value!!.find { it.name == selectedSubject.value }!!.id
//        viewModelScope.launch {
//            var result = repository.addTeacherSubject(
//                TOKEN_PREFIX + token,
//                AddSubjectDTO(subjectId, termId)
//            )
//            result.collect {
//                it!!.handleRepoResponse(
//                    onError = {},
//                    onLoading = {},
//                    onSuccess = {
//                        Log.i("subject",it.data.toString())
//                    }
//                )
//            }
//        }
    }

    fun getGrades() {

//        viewModelScope.launch {
//            var res = repository.getGrades()
//            res.collect {
//                it.handleRepoResponse(
//                    onLoading = {
//                    },
//                    onError = {
//                    },
//                    onSuccess = {
//                        grades.value = it.data
//                    },
//                )
//            }
//        }
    }


    fun getSubjects() {
//        val token = NadrisApplication.currentUserLocalData!!.Token
//        viewModelScope.launch {
//            var res = repository.getGradeSubjects(gradeId, token)
//            res.collect {
//                it.handleRepoResponse(
//                    onLoading = {
//                    },
//                    onError = {
//                    },
//                    onSuccess = {
//                        subjects.value = it.data
//                    },
//                )
//
//            }
//        }
    }


}