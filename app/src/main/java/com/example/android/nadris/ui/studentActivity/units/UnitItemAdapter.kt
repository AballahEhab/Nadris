package com.example.android.nadris.ui.studentActivity.units

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.database.models.SubjectUnit
import com.example.android.nadris.databinding.ItemExpandableUnitBinding
import com.example.android.nadris.repository.LocalDataSource
import com.example.android.nadris.util.isVisible
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Scope


class UnitItemAdapter @Inject constructor(val viewModel: UnitsViewModel) :
    RecyclerView.Adapter<UnitItemAdapter.UnitItemViewHolder>() {
    var expandedItemIndex: Int = -1

    private val differCalback = object : DiffUtil.ItemCallback<SubjectUnit>() {
        override fun areItemsTheSame(oldItem: SubjectUnit, newItem: SubjectUnit): Boolean {
            return oldItem.unitId == newItem.unitId
        }

        override fun areContentsTheSame(oldItem: SubjectUnit, newItem: SubjectUnit): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCalback)

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
        val unitData = differ.currentList[position]
        holder.unitName.text = unitData.name
        holder.unitImage.setImageResource(unitData.icon)
        holder.unitLessonList.isVisible(unitData.lessonsVisibility)

        Log.v("vsg",holder.toString())
        // set adapter to lessons recycler view if the lessons is visible and the adapter is null
        if(unitData.lessonsVisibility && holder.unitLessonList.adapter == null){
            val list = viewModel.unitLessons.value!!.filter {
                it.FKUnitId == unitData.unitId
            }
            Log.v("lessons",list.toString())
            val adapter = LessonItemAdapter(list)
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
        differ.currentList[expandedItemIndex].toggleExpandUnit()
        notifyItemChanged(expandedItemIndex)
    }

    override fun getItemCount() = differ.currentList.size
}