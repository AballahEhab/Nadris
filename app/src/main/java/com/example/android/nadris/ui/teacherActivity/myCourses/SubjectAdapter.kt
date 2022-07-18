package com.example.android.nadris.ui.teacherActivity.addingNewCourse


    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.navigation.findNavController
    import androidx.recyclerview.widget.AsyncListDiffer
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.RecyclerView
    import com.example.android.nadris.database.models.DatabaseTeacherSubject
    import com.example.android.nadris.databinding.ItemRvSubTeacherBinding
    import com.example.android.nadris.ui.teacherActivity.myCourses.SubjectFragmentDirections

class SubjectAdapter() :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DatabaseTeacherSubject>() {
        override fun areItemsTheSame(oldItem: DatabaseTeacherSubject, newItem: DatabaseTeacherSubject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DatabaseTeacherSubject, newItem: DatabaseTeacherSubject): Boolean {
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
        holder.binding.numOfStudents.text = ""
        holder.binding.tvClass.text = data.grade
        holder.binding.tvSemester.text = data.term
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(SubjectFragmentDirections.actionTeacherMySubjectsFragmentToTeacherSubjectUnitsFragment(data.id))
        }
        //  holder.binding.imgSubTeach.setImageResource(R.drawable.ic_user)
    }

    class ViewHolder(var binding: ItemRvSubTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
