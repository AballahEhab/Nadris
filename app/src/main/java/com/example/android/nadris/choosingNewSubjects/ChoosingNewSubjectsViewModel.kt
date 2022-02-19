package com.example.android.nadris.choosingNewSubjects

import android.view.MenuItem
import android.widget.Switch
import androidx.lifecycle.ViewModel

class ChoosingNewSubjectsViewModel : ViewModel() {
//     var item:Int = 0

    val class_ = listOf("الصف الأول الإعدادي", "الصف الثاني الإعدادي", "الصف الثالث الإعدادي",
                         "الصف الأول الثانوي","الصف الثاني الثانوي","الصف الثالث الثانوي")
    var subject_ = listOf("الغه العربيه", "اللغه الإنجليزيه", "الرياضيات","الدراسات ","العلوم", "الدين")
    val semester_ = listOf("الترم الأول", "الترم الثاني")
//    val sr_ = listOf("Option 5", "Option 6", "Option 3", "Option 7")
//    fun onItemSelected(){
//        when(item){
//           0-> subject_=semester_
//            1->subject_=sr_
//
//        }
    //}


}