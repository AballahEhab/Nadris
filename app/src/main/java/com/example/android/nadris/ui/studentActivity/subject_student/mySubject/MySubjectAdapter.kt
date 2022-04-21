package com.example.android.nadris.ui.studentActivity.subject_student.mySubject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.database.models.StudentSubject
import com.example.android.nadris.databinding.ItemSubjectStudentBinding

class MySubjectAdapter: RecyclerView.Adapter<MySubjectAdapter.ViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<StudentSubject>(){
        override fun areItemsTheSame(oldItem: StudentSubject, newItem: StudentSubject): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: StudentSubject, newItem: StudentSubject): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ):ViewHolder {
        val v = ItemSubjectStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.tvNameSubjectTeacher.text = data.name
        holder.binding.tvCountStudentTeach.text = ""
        holder.binding.tvClass.text = data.grade
        holder.binding.tvSemester.text = data.term
        //  holder.binding.imgSubTeach.setImageResource(R.drawable.ic_user)
    }

    class ViewHolder(var binding: ItemSubjectStudentBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}