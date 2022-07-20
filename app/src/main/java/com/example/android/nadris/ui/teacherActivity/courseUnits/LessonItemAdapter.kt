package com.example.android.nadris.ui.teacherActivity.courseUnits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabaseCourseLesson
import com.example.android.nadris.databinding.ItemLessonBinding

class LessonItemAdapter : RecyclerView.Adapter<LessonItemAdapter.LessonItemViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<DatabaseCourseLesson>() {
        override fun areItemsTheSame(oldItem: DatabaseCourseLesson, newItem: DatabaseCourseLesson): Boolean {
            return oldItem.lessonID == newItem.lessonID
        }

        override fun areContentsTheSame(oldItem: DatabaseCourseLesson, newItem: DatabaseCourseLesson): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonBinding.inflate(inflater, parent, false)
        return LessonItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonItemViewHolder, position: Int) {
        val lessonData = differ.currentList[position]
        holder.binding.lessonTitle.text = lessonData.lessonName

        holder.binding.root.setOnClickListener {
            if (NadrisApplication.currentDatabaseUser?.IsATeacher!!)
                it.findNavController()
                    .navigate(UnitsFragmentDirections.actionTeacherSubjectUnitsFragmentToAddingSectionsFragment(
                        5,
                        0,
                        position,
                        lessonData.lessonName))
            else
                it.findNavController() // please change this to navigate to viewLesson page
                    .navigate(UnitsFragmentDirections.actionTeacherSubjectUnitsFragmentToAddingSectionsFragment(
                        5,
                        0,
                        position,
                        lessonData.lessonName))

        }
    }

    override fun getItemCount() = differ.currentList.size

    class LessonItemViewHolder(val binding: ItemLessonBinding) :
        RecyclerView.ViewHolder(binding.root)
}
