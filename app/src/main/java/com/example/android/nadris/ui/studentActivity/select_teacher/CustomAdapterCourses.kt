package com.example.android.nadris


import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.ItemRvTeachersBinding
import com.example.android.nadris.ui.studentActivity.select_teacher.TeachersCoursesViewModel
import com.example.android.nadris.util.isVisible

class CustomAdapterCourses(val viewModel:TeachersCoursesViewModel) : RecyclerView.Adapter<CustomAdapterCourses.Viewholder>() {

//    private val differCallback = object : DiffUtil.ItemCallback<TeachersCoursesDTO>() {
//        override fun areItemsTheSame(oldItem: TeachersCoursesDTO, newItem: TeachersCoursesDTO): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: TeachersCoursesDTO, newItem: TeachersCoursesDTO): Boolean {
//            return oldItem == newItem
//        }
//    }

//    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : Viewholder {

        val v = ItemRvTeachersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return Viewholder(v)
    }

//    override fun getItemCount(): Int {
//        return differ.currentList.size
//    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
//        val data = differ.currentList[position]
//        if(!data.teacherProfileImageB64.isNullOrEmpty()){
//            NadrisApplication.instance?.let {
//                Converter(it.applicationContext).convertFromBase64ToBitmap(data.teacherProfileImageB64,
//                    data.id.toString())
//            }
//
//        }
//        val file = File(NadrisApplication.instance?.applicationContext?.cacheDir,
//            data.id.toString())
        var img: Bitmap? = null
//        img = BitmapFactory.decodeFile(file.absolutePath)
        if(true /*data.isJoined == false*/) {
//            holder.binding.tvNameTeachers.text = data.teacherName
//            holder.binding.numOfStudents.text = data.NumOfStudents.toString()
            holder.binding.ivTeachers.setImageBitmap(img!!)

            holder.binding.tvAddTeacher.setOnClickListener {
//                viewModel.registerAStudentInACourse(data.id)
                holder.binding.tvAddTeacher.isVisible(false)

            }
        }else{
//            holder.binding.tvNameTeachers.text = data.teacherName
//            holder.binding.numOfStudents.text = data.NumOfStudents.toString()
            holder.binding.ivTeachers.setImageBitmap(img!!)
            holder.binding.tvAddTeacher.isVisible(false)
        }
        holder.binding.ivTeachers.setOnClickListener{
//            it.findNavController().navigate(TeachersCoursesFragmentDirections.actionStudentTeachersForASubjectFragmentToPublicProfileFragment(data.teacherId))
        }



        holder.itemView.setOnClickListener {

//            it.findNavController().navigate(TeachersCoursesFragmentDirections.actionStudentTeachersForASubjectFragmentToStudentSubjectUnitsFragment(data.id))
        }


    }


    class Viewholder(var binding: ItemRvTeachersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}
