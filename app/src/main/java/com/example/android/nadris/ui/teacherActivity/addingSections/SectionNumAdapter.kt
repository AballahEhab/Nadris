package com.example.android.nadris.ui.teacherActivity.addingSections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.ItemSectionNumBinding

class SectionNumAdapter(val numOfItems:Int,val viewModel:AddingSectionsViewModel): RecyclerView.Adapter<SectionNumAdapter.SectionNumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionNumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSectionNumBinding.inflate(inflater,parent,false)
        return SectionNumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionNumViewHolder, position: Int) {
        val sectionNumBtn = holder.binding.sectionNum
        sectionNumBtn.text = (position+1).toString()
        sectionNumBtn.setOnClickListener {
            viewModel.setSelectedSection(position)
        }
    }

    override fun getItemCount() = numOfItems


    class SectionNumViewHolder(val binding:ItemSectionNumBinding):RecyclerView.ViewHolder(binding.root){

    }
}