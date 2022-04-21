package com.example.android.nadris.ui.studentActivity.units

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.database.models.UnitLessons
import com.example.android.nadris.databinding.ItemExpandableUnitBinding
import com.example.android.nadris.util.isVisible
import javax.inject.Inject


class UnitItemAdapter @Inject constructor(val viewModel: UnitsViewModel) :
    RecyclerView.Adapter<UnitItemAdapter.UnitItemViewHolder>() {
    var expandedItemIndex: Int = -1

    private val differCallback = object : DiffUtil.ItemCallback<UnitLessons>() {
        override fun areItemsTheSame(oldItem: UnitLessons, newItem: UnitLessons): Boolean {
            return oldItem.unit.unitId == newItem.unit.unitId
        }

        override fun areContentsTheSame(oldItem: UnitLessons, newItem: UnitLessons): Boolean {
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
        val unit = unitData.unit
        holder.binding.unit = unit

         holder.binding.unitIcon.setImageResource(unitData.unit.icon)


        holder.binding.lessonsList.isVisible(unit.lessonsVisibility)
        if (unit.lessonsVisibility && holder.binding.lessonsList.adapter == null) {
            val adapter = LessonItemAdapter()
            holder.binding.lessonsList.adapter = adapter
            adapter.differ.submitList(unitData.lessons)
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
        differ.currentList[expandedItemIndex].unit.toggleExpandUnit()
        notifyItemChanged(expandedItemIndex)
    }

    override fun getItemCount() = differ.currentList.size


    class UnitItemViewHolder(val binding: ItemExpandableUnitBinding) : RecyclerView.ViewHolder(binding.root)

}