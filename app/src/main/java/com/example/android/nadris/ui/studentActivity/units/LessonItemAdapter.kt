package com.example.android.nadris.ui.studentActivity.units

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.LessonItemBinding
import com.example.android.nadris.database.models.Lesson

class LessonItemAdapter(val lessonList: List<Lesson>) :
    RecyclerView.Adapter<LessonItemAdapter.LessonItemViewHolder>() {

    class LessonItemViewHolder(val binding: LessonItemBinding) : RecyclerView.ViewHolder(binding.root){
        val lessonTitle = binding.lessonTitle
        val lessonNum = binding.lessonNum
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LessonItemBinding.inflate(inflater,parent,false)
        return LessonItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonItemViewHolder, position: Int) {
        val lessonData = lessonList[position]
        holder.lessonTitle.text = lessonData.lessonName
        holder.lessonNum.text = lessonData.lessonNum
    }

    override fun getItemCount() = lessonList.size
}
