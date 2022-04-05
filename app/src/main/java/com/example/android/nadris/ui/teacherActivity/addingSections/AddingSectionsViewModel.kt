package com.example.android.nadris.ui.teacherActivity.addingSections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddingSectionsViewModel @Inject constructor() : ViewModel() {


    var SectionAsHtml: MutableLiveData<String> = MutableLiveData("")
    var selectedSectionNum: MutableLiveData<Int> = MutableLiveData(0)

     var sectionsList = MutableLiveData(MutableList(1){ num->"Section $num"})

    init {
        setNumOfSections(1)
    }

    fun setNumOfSections(numOfSections:Int){
        sectionsList.value = MutableList(numOfSections){ num->"Section ${num+1}"}
    }

    fun setSelectedSection(sectionNum:Int){
        selectedSectionNum.value = sectionNum
    }

}