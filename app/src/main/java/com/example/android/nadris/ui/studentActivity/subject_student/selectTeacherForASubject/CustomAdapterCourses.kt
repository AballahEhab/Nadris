package com.example.android.nadris.ui.studentActivity.subject_student.selectTeacherForASubject


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.data.models.TeachersCoursesModel
import com.example.android.nadris.databinding.ItemRvTeachersBinding
import com.example.android.nadris.util.isVisible

class CustomAdapterCourses(val viewModel:TeachersCoursesViewModel) : RecyclerView.Adapter<CustomAdapterCourses.Viewholder>() {

    private val differCallback = object : DiffUtil.ItemCallback<TeachersCoursesModel>() {
        override fun areItemsTheSame(oldItem: TeachersCoursesModel, newItem: TeachersCoursesModel): Boolean {
            return oldItem.courseId == newItem.courseId
        }

        override fun areContentsTheSame(oldItem: TeachersCoursesModel, newItem: TeachersCoursesModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : Viewholder {

        val v = ItemRvTeachersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return Viewholder(v)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val data = differ.currentList[position]
//        TODO: teacher image will work on it later
//        if(!data.teacherProfileImageB64.isNullOrEmpty()){
//            NadrisApplication.instance?.let {
//                Converter(it.applicationContext).convertFromBase64ToBitmap(data.teacherProfileImageB64,
//                    data.id.toString())
//            }
//
//        }
//        val file = File(NadrisApplication.instance?.applicationContext?.cacheDir,
//            data.id.toString())
//        var img: Bitmap? = null
//        img = BitmapFactory.decodeFile(file.absolutePath)
        if(!data.isStudentJoined ) {
            holder.binding.tvAddTeacher.isVisible(true)
            holder.binding.tvAddTeacher.setOnClickListener {
                viewModel.subscribeAStudentToACourse(data.courseId)
            }
        }else{
            holder.binding.tvAddTeacher.isVisible(false)
        }

        holder.binding.tvNameTeachers.text = data.teacherName
        holder.binding.numOfStudents.text = data.numOfStudents.toString()
//        holder.binding.ivTeachers.setImageBitmap(img!!)

        holder.binding.ivTeachers.setOnClickListener{
//            it.findNavController().navigate(TeachersCoursesFragmentDirections.actionStudentTeachersForASubjectFragmentToPublicProfileFragment(data.teacherId))
        }

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(TeachersCoursesFragmentDirections.actionStudentTeachersForASubjectFragmentToStudentSubjectUnitsFragment(data.courseId))
        }

        holder.itemView.setOnClickListener {
//            it.findNavController().navigate(TeachersCoursesFragmentDirections.actionStudentTeachersForASubjectFragmentToStudentSubjectUnitsFragment(data.courseId))
        }
    }


    class Viewholder(var binding: ItemRvTeachersBinding) : RecyclerView.ViewHolder(binding.root)


}
