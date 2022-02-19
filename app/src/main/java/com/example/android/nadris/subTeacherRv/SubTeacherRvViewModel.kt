package com.example.android.nadris.subTeacherRv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.R
import com.example.android.nadris.dataRvsubTeach

class SubTeacherRvViewModel : ViewModel() {
    private  var list= MutableLiveData<List<dataRvsubTeach>>()
    fun getdata(): MutableLiveData<List<dataRvsubTeach>> {

        val subjects = mutableListOf<dataRvsubTeach>()
        subjects.add(
            dataRvsubTeach(1,"الفيزياء",160,"الثالث الثانوي","الفصل الدراسي الاول",
                R.drawable.icon_plus)
        )
        subjects.add(
            dataRvsubTeach(2,"الكيمياء",210,"الثاني الثانوي","الفصل الدراسي الثاني",
                R.drawable.icon_plus)
        )
        subjects.add(
            dataRvsubTeach(3,"الفيزياء",160,"الثالث الثانوي","الفصل الدراسي الاول",
                R.drawable.icon_plus)
        )
        subjects.add(
            dataRvsubTeach(4,"الكيمياء",210,"الثاني الثانوي","الفصل الدراسي الثاني",
                R.drawable.icon_plus)
        )
        subjects.add(
            dataRvsubTeach(5,"الفيزياء",160,"الثالث الثانوي","الفصل الدراسي الاول",
                R.drawable.icon_plus)
        )
        subjects.add(
            dataRvsubTeach(6,"الكيمياء",210,"الثاني الثانوي","الفصل الدراسي الثاني",
                R.drawable.icon_plus)
        )

        list.value=subjects
        return list
    }}