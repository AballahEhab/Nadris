package com.example.android.nadris.ui.studentActivity.units

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.ItemExpandableUnitBinding
import com.example.android.nadris.database.models.SubjectUnit
import com.example.android.nadris.util.isVisible

class UnitItemAdapter (val unitsList:List<SubjectUnit>): RecyclerView.Adapter<UnitItemAdapter.UnitItemViewHolder>() {
    var expandedItemIndex:Int = -1

    class UnitItemViewHolder(val binding: ItemExpandableUnitBinding) : RecyclerView.ViewHolder(binding.root) {
        val unitName = binding.unitName
        val unitImage = binding.unitIcon
        val unitLessonList = binding.lessonsList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemExpandableUnitBinding.inflate(inflater,parent,false)
        return UnitItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnitItemViewHolder, position: Int) {
        val unitData = unitsList[position]
        holder.unitName.text = unitData.name
        holder.unitImage.setImageResource(unitData.icon)
        holder.unitLessonList.isVisible(unitData.lessonsVisibility)

        Log.v("vsg",holder.toString())
        // set adapter to lessons recycler view if the lessons is visible and the adapter is null
        if(unitData.lessonsVisibility && holder.unitLessonList.adapter == null){
            val adapter = LessonItemAdapter(unitData.lessons)
            holder.unitLessonList.adapter = adapter
        }


        holder.binding.root.setOnClickListener {
            if (expandedItemIndex == -1){
                expandedItemIndex = position
                toggleUnitExpansion()
            } else if (expandedItemIndex == position){
                toggleUnitExpansion()
                expandedItemIndex = -1
            }else{
                toggleUnitExpansion()
                expandedItemIndex = position
                toggleUnitExpansion()
            }


        }


    }

    private fun toggleUnitExpansion(){
        unitsList[expandedItemIndex].toggleExpandUnit()
        notifyItemChanged(expandedItemIndex)

    }

    override fun getItemCount() = unitsList.size
}