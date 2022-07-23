package com.example.android.nadris.ui.teacherActivity.myCourses


    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.AsyncListDiffer
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.RecyclerView
    import com.example.android.nadris.R
    import com.example.android.nadris.database.models.DatabaseTeacherCourse
    import com.example.android.nadris.databinding.ItemRvSubTeacherBinding

class SubjectAdapter() :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DatabaseTeacherCourse>() {
        override fun areItemsTheSame(oldItem: DatabaseTeacherCourse, newItem: DatabaseTeacherCourse): Boolean {
            return oldItem.courseId == newItem.courseId
        }

        override fun areContentsTheSame(oldItem: DatabaseTeacherCourse, newItem: DatabaseTeacherCourse): Boolean {
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
        holder.binding.tvNameSubjectTeacher.text = data.subjectName
        holder.binding.numOfStudents.text = ""
        holder.binding.tvClass.text = data.gradeName
//        holder.itemView.setOnClickListener {
//            it.findNavController().navigate(SubjectFragmentDirections.actionTeacherMySubjectsFragmentToTeacherSubjectUnitsFragment(data.courseId))
//        }
          holder.binding.imgSubTeach.setImageResource(R.drawable.ic_user)
    }

    class ViewHolder(var binding: ItemRvSubTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
