package com.example.android.nadris.ui.teacherActivity.subjects_teacher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.R
import com.example.android.nadris.ui.teacherActivity.choosingNewSubjects.dataRvsubTeach

class SubTeacherRvViewModel : ViewModel() {
    //retrive data from choosing new subject
    var select_class=""
    var select_subject=""
    var select_semester=""




    private  var list= MutableLiveData<List<dataRvsubTeach>>()
    fun getdata(): MutableLiveData<List<dataRvsubTeach>> {

        val subjects = mutableListOf<dataRvsubTeach>()
        subjects.add(
            dataRvsubTeach(1,"الفيزياء",160,"الثالث الثانوي","الفصل الدراسي الاول",
                R.drawable.icon_physics
            )
        )
        subjects.add(
            dataRvsubTeach(2,"الكيمياء",210,"الثاني الثانوي","الفصل الدراسي الثاني",
                R.drawable.icon_physics
            )
        )
        subjects.add(
            dataRvsubTeach(3,"الفيزياء",160,"الثالث الثانوي","الفصل الدراسي الاول",
                R.drawable.icon_physics
            )
        )
        subjects.add(
            dataRvsubTeach(4,"الكيمياء",210,"الثاني الثانوي","الفصل الدراسي الثاني",
                R.drawable.icon_physics
            )
        )
        subjects.add(
            dataRvsubTeach(5,"الفيزياء",160,"الثالث الثانوي","الفصل الدراسي الاول",
                R.drawable.icon_physics
            )
        )
        subjects.add(
            dataRvsubTeach(6,"الكيمياء",210,"الثاني الثانوي","الفصل الدراسي الثاني",
                R.drawable.icon_physics
            )
        )

        list.value=subjects
        return list
    }}