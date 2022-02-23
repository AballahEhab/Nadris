package com.example.android.nadris.ui.studentActivity.subject_student
/**
 * @author mohammed sarhan
 * **/
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.R
import com.example.android.nadris.database.DatabaseSubject


class SubjectsRvFragmentViewModel : ViewModel() {

    private var list=MutableLiveData<List<DatabaseSubject>>();
    fun  getdata(): MutableLiveData<List<DatabaseSubject>> {
        val subjects =mutableListOf<DatabaseSubject>()
        subjects.add(DatabaseSubject(1,"الفيزياء",15, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(2,"الكيمياء",20, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(3,"اللغه العربيه",118, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(4,"الأحياء",75, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(5,"الجبر",36, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(6,"الفيزياء",15, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(7,"الكيمياء",20, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(8,"اللغه العربيه",118, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(9,"الأحياء",75, R.drawable.icon_physics))
        subjects.add(DatabaseSubject(10,"الجبر",36, R.drawable.icon_physics))
        list.value = subjects
        return list
    }
    
}