package com.example.android.nadris.ui.studentActivity.units

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.database.models.DatabaseUnitLessons
import com.example.android.nadris.databinding.ItemExpandableUnitBinding
import com.example.android.nadris.util.isVisible


class UnitItemAdapter (val viewModel: UnitsViewModel) :
    RecyclerView.Adapter<UnitItemAdapter.UnitItemViewHolder>() {
    var expandedItemIndex: Int = -1

    private val differCallback = object : DiffUtil.ItemCallback<DatabaseUnitLessons>() {
        override fun areItemsTheSame(oldItem: DatabaseUnitLessons, newItem: DatabaseUnitLessons): Boolean {
            return oldItem.unitDatabase.unitId == newItem.unitDatabase.unitId
        }

        override fun areContentsTheSame(oldItem: DatabaseUnitLessons, newItem: DatabaseUnitLessons): Boolean {
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


        holder.binding.lessonsList.isVisible(unit.lessonsVisibility)
        if (unit.lessonsVisibility && holder.binding.lessonsList.adapter == null) {
            val adapter = LessonItemAdapter()
            holder.binding.lessonsList.adapter = adapter
            adapter.differ.submitList(unitData.databaseLessons)
    }

        holder.binding.root.setOnClickListener {
            if (expandedItemIndex == -1) {
                expandedItemIndex = position
                toggleUnitExpansion()
            } else if (expandedItemIndex == position) {
                toggleUnitExpansion()
                expandedItemIndex = -1
            } else {
                toggleUnitExpansion()
                expandedItemIndex = position
                toggleUnitExpansion()
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