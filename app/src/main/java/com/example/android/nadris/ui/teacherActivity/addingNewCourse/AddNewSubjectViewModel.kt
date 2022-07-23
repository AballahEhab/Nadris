package com.example.android.nadris.ui.teacherActivity.addingNewCourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.network.firebase.dtos.Course
import com.example.android.nadris.network.firebase.dtos.Grade
import com.example.android.nadris.network.firebase.dtos.Subject
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewSubjectViewModel @Inject constructor(
    val repository: Repository,
//    @ApplicationContext val context: Context,
) : ViewModel() {

    var gradesList: MutableLiveData<List<Grade>> = MutableLiveData<List<Grade>>()
    private var selectedGradeRef: MutableLiveData<DocumentReference> =
        MutableLiveData<DocumentReference>()
    private val _gradesResult: MutableLiveData<Result<MutableList<Grade>>> =
        MutableLiveData<Result<MutableList<Grade>>>()
    val gradesResult: LiveData<Result<MutableList<Grade>>> get() = _gradesResult

    var subjectsList: MutableLiveData<List<Subject>> = MutableLiveData<List<Subject>>()
    var selectedSubjectId: MutableLiveData<String> = MutableLiveData<String>()
    private val _subjectResult: MutableLiveData<Result<List<Subject>>> =
        MutableLiveData<Result<List<Subject>>>()
    val subjectResult: LiveData<Result<List<Subject>>> get() = _subjectResult

    var termList: ArrayList<String> = ArrayList()
    var selectedTerm: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    val loadingState: LiveData<Boolean> get() = _loadingState

    private val _addBtnEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val addBtnEnabled: LiveData<Boolean> get() = _addBtnEnabled

    private val _addingCourseResult: MutableLiveData<Result<Boolean>> =
        MutableLiveData<Result<Boolean>>()
    val addingCourseResult: LiveData<Result<Boolean>> get() = _addingCourseResult


    fun getGrades() {
        enableLoading()
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGradesWithFlows().collect {
                _gradesResult.postValue(it)
            }
        }
    }

    fun setSelectedGradeIndex(index: Int) {
        selectedGradeRef.value = gradesList.value?.get(index)?.gradeReference!!
        getSubjects()
    }

    fun getSelectedGrade() =
        selectedGradeRef.value

    private fun getSubjects() {
        enableLoading()
        viewModelScope.launch(Dispatchers.IO) {
            _subjectResult.postValue(repository.getSubjectsWithGrade(selectedGradeRef.value!!))
        }
    }

    fun validateInputFields() {
        _addBtnEnabled.value = !(
                selectedSubjectId.value.isNullOrEmpty()
                        || selectedGradeRef.value == null
                        || selectedTerm.value == null)
    }

    fun createCourse() {
        enableLoading()
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNewCourse(Course(
                gradeRef = selectedGradeRef.value,
                subjectId = selectedSubjectId.value!!,
                term = selectedTerm.value!!,
                ownerTeacherID = NadrisApplication.currentDatabaseUser?.userID!!
            )
            ).collect { _addingCourseResult.postValue(it) }
            NadrisApplication.instance?.updateUserFirebaseUser()
        }
    }

    fun enableLoading() {
        _loadingState.value = true
    }

    fun disableLoading() {
        _loadingState.value = false
    }

    fun setSubject(index: Int) {
        selectedSubjectId.value = subjectsList.value?.get(index)?.subject_id
    }


}
