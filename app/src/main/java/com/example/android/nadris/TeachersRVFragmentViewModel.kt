package com.example.android.nadris
/**
 * @author mohammed sarhan
 * **/
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TeachersRVFragmentViewModel : ViewModel() {
   private var list=MutableLiveData<List<dataRvTeach>>();
    fun  getdata(): MutableLiveData<List<dataRvTeach>> {
        var teachers= mutableListOf<dataRvTeach>()
        teachers.add(dataRvTeach(1,"محمد مصطفي", 8, R.drawable.icon_parent))
        teachers.add(dataRvTeach(2,"شكري فضل", 8, R.drawable.icon_parent))
        teachers.add(dataRvTeach(3,"عبدالله إيهاب", 8, R.drawable.icon_parent))
        teachers.add(dataRvTeach(4,"عبدالله صلاح", 8, R.drawable.icon_parent))
        teachers.add(dataRvTeach(5,"محمد مصطفي", 8, R.drawable.icon_parent))
        teachers.add(dataRvTeach(6,"شكري فضل", 8, R.drawable.icon_parent))
        list.value = teachers
     return list
    }
}


