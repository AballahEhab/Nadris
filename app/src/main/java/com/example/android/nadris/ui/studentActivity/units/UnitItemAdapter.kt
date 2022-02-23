package com.example.android.nadris.ui.studentActivity.units

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.ExpandableUnitItemBinding
import com.example.android.nadris.domain.SubjectUnit
import com.example.android.nadris.util.isVisible

class UnitItemAdapter (val unitsList:List<SubjectUnit>): RecyclerView.Adapter<UnitItemAdapter.UnitItemViewHolder>() {
    var expandedItemIndex:Int = -1

    class UnitItemViewHolder(val binding: ExpandableUnitItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val unitName = binding.unitName
        val unitImage = binding.unitIcon
        val unitLessonList = binding.lessonsList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpandableUnitItemBinding.inflate(inflater,parent,false)
        return UnitItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnitItemViewHolder, position: Int) {
        val unitData = unitsList[position]
        holder.unitName.text = unitData.name
        holder.unitImage.setImageResource(unitData.icon)
        holder.unitLessonList.isVisible(unitData.lessonsVisibility)

        holder.binding.root.setOnClickListener {
            if (expandedItemIndex == -1){
                expandedItemIndex = position

                toggleUnitExpansion(position)

            } else if (expandedItemIndex == position){
                expandedItemIndex = -1

                toggleUnitExpansion(position)

            }else{

                toggleUnitExpansion(expandedItemIndex)

                expandedItemIndex = position

                toggleUnitExpansion(position)

            }


        }
    }

    private fun toggleUnitExpansion(index: Int){
        unitsList[index].toggleExpandUnit()
        notifyItemChanged(index)

    }

    override fun getItemCount() = unitsList.size
}