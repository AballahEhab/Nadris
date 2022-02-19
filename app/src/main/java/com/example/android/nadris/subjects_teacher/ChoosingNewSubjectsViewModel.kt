package com.example.android.nadris.subjects_teacher

import android.view.MenuItem
import android.widget.Switch
import androidx.lifecycle.ViewModel

class ChoosingNewSubjectsViewModel : ViewModel() {

    //getData will selected from spiner
    var select_class:String=""
    var select_subject:String=""
    var select_semester:String=""

    val class_ = listOf("الصف الأول الإعدادي", "الصف الثاني الإعدادي", "الصف الثالث الإعدادي",
                         "الصف الأول الثانوي","الصف الثاني الثانوي","الصف الثالث الثانوي")
    var subject_ = listOf("الغه العربيه", "اللغه الإنجليزيه", "الرياضيات","الدراسات ","العلوم", "الدين")
    val semester_ = listOf("الترم الأول", "الترم الثاني")



}