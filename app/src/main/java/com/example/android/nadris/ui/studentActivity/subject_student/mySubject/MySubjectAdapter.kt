package com.example.android.nadris.ui.studentActivity.subject_student.mySubject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.database.models.DatabaseStudentSubject
import com.example.android.nadris.databinding.ItemSubjectStudentBinding

class MySubjectAdapter: RecyclerView.Adapter<MySubjectAdapter.ViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<DatabaseStudentSubject>(){
        override fun areItemsTheSame(oldItem: DatabaseStudentSubject, newItem: DatabaseStudentSubject): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: DatabaseStudentSubject, newItem: DatabaseStudentSubject): Boolean {
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
        holder.binding.teacherName.text =data.teacherName
        holder.binding.tvEvaluation.text = data.rate.toString()
        holder.binding.textViewProgress.text = data.progress.toString()
        holder.binding.progressBar.progress= data.progress.toInt()
          holder.binding.imgSubTeach.setImageResource(R.drawable.ic_user)
//        holder.binding.remove.setOnClickListener{
//            val dialog =AlertDialog.Builder(this)
//            dialog.apply {
//                setTitle("remove your course")
//                setMessage("Are you sure you want to remove your course? ")
//                setNegativeButton("No"){negative,_ ->
//                    negative.dismiss()
//                }
////                setPositiveButton("Yes"){positive,_ ->
////                    startActivity()
////                }
//            }
//        }
    }

    class ViewHolder(var binding: ItemSubjectStudentBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}