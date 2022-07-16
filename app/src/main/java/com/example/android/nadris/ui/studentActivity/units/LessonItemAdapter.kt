package com.example.android.nadris.ui.studentActivity.units

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.database.models.DatabaseLesson
import com.example.android.nadris.databinding.ItemLessonBinding

class LessonItemAdapter : RecyclerView.Adapter<LessonItemAdapter.LessonItemViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<DatabaseLesson>() {
        override fun areItemsTheSame(oldItem: DatabaseLesson, newItem: DatabaseLesson): Boolean {
            return oldItem.lessonID == newItem.lessonID
        }

        override fun areContentsTheSame(oldItem: DatabaseLesson, newItem: DatabaseLesson): Boolean {
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
            it.findNavController().navigate(UnitsFragmentDirections.actionTeacherSubjectUnitsFragmentToAddingSectionsFragment(5,0,position,lessonData.lessonName))
        }
    }

    override fun getItemCount() = differ.currentList.size

    class LessonItemViewHolder(val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root)
}
