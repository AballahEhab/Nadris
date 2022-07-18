package com.example.android.nadris.ui.studentActivity.subject_student.exploreCourses


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.ItemRvSubjectsBinding

class customAdapterRVsub()
    : RecyclerView.Adapter<customAdapterRVsub.ViewHolder>() {

//    packagerivate val differCallback=object : DiffUtil.ItemCallback<SubjectDTO>(){
//        override fun areItemsTheSame(oldItem: SubjectDTO, newItem: SubjectDTO): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: SubjectDTO, newItem: SubjectDTO): Boolean {
//            return oldItem == newItem
//        }
//
//    }

//    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        var v = ItemRvSubjectsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
//        return differ.currentList.size
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val data = differ.currentList[position]

//        holder.binding.tvNameSubject.text = data.name
//        holder.binding.numberOfTeachers.text = data.numOfTeachers.toString()
        holder.itemView.setOnClickListener {
//           it.findNavController().navigate(SubjectFragmentDirections.actionSubjectFragmentToStudentTeachersForASubjectFragment(data.id))
        }

    }


    class ViewHolder(var binding: ItemRvSubjectsBinding) : RecyclerView.ViewHolder(binding.root){

    }
}
