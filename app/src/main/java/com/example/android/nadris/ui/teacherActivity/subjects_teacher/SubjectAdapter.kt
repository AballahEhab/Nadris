package com.example.android.nadris.ui.teacherActivity.choosingNewSubjects


    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.navigation.findNavController
    import androidx.recyclerview.widget.AsyncListDiffer
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.RecyclerView
    import com.example.android.nadris.database.models.TeacherSubject
    import com.example.android.nadris.databinding.ItemRvSubTeacherBinding
    import com.example.android.nadris.ui.teacherActivity.subjects_teacher.SubjectFragmentDirections

class SubjectAdapter() :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<TeacherSubject>() {
        override fun areItemsTheSame(oldItem: TeacherSubject, newItem: TeacherSubject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TeacherSubject, newItem: TeacherSubject): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): ViewHolder {
        val v = ItemRvSubTeacherBinding.inflate(
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
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(SubjectFragmentDirections.actionSubjectFragmentToSubjectUnits(data.id))
        }
        //  holder.binding.imgSubTeach.setImageResource(R.drawable.ic_user)
    }

    class ViewHolder(var binding: ItemRvSubTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
