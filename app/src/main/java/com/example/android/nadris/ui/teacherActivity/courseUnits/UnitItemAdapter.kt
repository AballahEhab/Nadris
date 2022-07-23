package com.example.android.nadris.ui.teacherActivity.courseUnits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.database.models.DatabaseCourseUnitWithLessonsAndSections
import com.example.android.nadris.databinding.ItemExpandableUnitBinding
import com.example.android.nadris.util.isVisible


class UnitItemAdapter (val viewModel: UnitsViewModel) :
    RecyclerView.Adapter<UnitItemAdapter.UnitItemViewHolder>() {
    var expandedItemIndex: Int = -1

    private val differCallback = object : DiffUtil.ItemCallback<DatabaseCourseUnitWithLessonsAndSections>() {
        override fun areItemsTheSame(oldItem: DatabaseCourseUnitWithLessonsAndSections, newItem: DatabaseCourseUnitWithLessonsAndSections): Boolean {
            return oldItem.unitDatabase.unitId == newItem.unitDatabase.unitId
        }

        override fun areContentsTheSame(oldItem: DatabaseCourseUnitWithLessonsAndSections, newItem: DatabaseCourseUnitWithLessonsAndSections): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitItemViewHolder {
        return UnitItemViewHolder(
            ItemExpandableUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UnitItemViewHolder, position: Int) {

        val unitData = differ.currentList[position]

        val unit = unitData.unitDatabase

        holder.binding.unit = unit

         holder.binding.unitIcon.setImageResource(unitData.unitDatabase.icon)

        holder.binding.lessonsList.adapter = LessonItemAdapter()

        if (unit.lessonsVisibility) {
            val lessons = unitData.databaseCourseLessons.map{
                it.Lesson
            }
            (holder.binding.lessonsList.adapter as LessonItemAdapter).differ.submitList(lessons)
        }

        holder.binding.lessonsList.isVisible(unit.lessonsVisibility)

        holder.binding.root.setOnClickListener {
            when (expandedItemIndex) {
                -1 -> {
                    expandedItemIndex = position
                    toggleUnitExpansion()
                }
                position -> {
                    toggleUnitExpansion()
                    expandedItemIndex = -1
                }
                else -> {
                    toggleUnitExpansion()
                    expandedItemIndex = position
                    toggleUnitExpansion()
                }
            }
        }
    }

    private fun toggleUnitExpansion() {
        differ.currentList[expandedItemIndex].unitDatabase.toggleExpandUnit()
        notifyItemChanged(expandedItemIndex)
    }

    override fun getItemCount() = differ.currentList.size


    class UnitItemViewHolder(val binding: ItemExpandableUnitBinding) : RecyclerView.ViewHolder(binding.root)

}