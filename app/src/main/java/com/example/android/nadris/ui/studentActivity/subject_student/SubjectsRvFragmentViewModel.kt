package com.example.android.nadris.ui.studentActivity.subject_student
/**
 * @author mohammed sarhan
 * **/
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.R
import com.example.android.nadris.dataRVsubITEM


class SubjectsRvFragmentViewModel : ViewModel() {

    private var list=MutableLiveData<List<dataRVsubITEM>>();
    fun  getdata(): MutableLiveData<List<dataRVsubITEM>> {
        val subjects =mutableListOf<dataRVsubITEM>()
        subjects.add(dataRVsubITEM(1,"الفيزياء",15, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(2,"الكيمياء",20, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(3,"اللغه العربيه",118, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(4,"الأحياء",75, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(5,"الجبر",36, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(6,"الفيزياء",15, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(7,"الكيمياء",20, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(8,"اللغه العربيه",118, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(9,"الأحياء",75, R.drawable.icon_physics))
        subjects.add(dataRVsubITEM(10,"الجبر",36, R.drawable.icon_physics))
        list.value = subjects
        return list
    }
    
}