package com.example.android.nadris.ui.studentActivity.subject_student.exploreCourses


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.databinding.ItemRvSubjectsBinding
import com.example.android.nadris.network.firebase.dtos.Subject
import com.example.android.nadris.ui.studentActivity.subject_student.SubjectFragmentDirections

class customAdapterRVsub()
    : RecyclerView.Adapter<customAdapterRVsub.ViewHolder>() {

    private val differCallback=object : DiffUtil.ItemCallback<Subject>(){
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem.subject_id == newItem.subject_id
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        var v = ItemRvSubjectsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]

        holder.binding.tvNameSubject.text = if(NadrisApplication.instance?.lang == "eng") data.name_en else data.name_ar
        holder.binding.numberOfTeachers.text = data.numOfTeachersHaveCourseForSubject.toString()
        holder.itemView.setOnClickListener {
           it.findNavController().navigate(SubjectFragmentDirections.actionSubjectFragmentToStudentTeachersForASubjectFragment(data.subject_id))
        }

    }


    class ViewHolder(var binding: ItemRvSubjectsBinding) : RecyclerView.ViewHolder(binding.root)
}
