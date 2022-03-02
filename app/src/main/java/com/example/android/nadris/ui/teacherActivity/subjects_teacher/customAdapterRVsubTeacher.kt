package com.example.android.nadris.ui.teacherActivity.choosingNewSubjects


    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.AsyncListDiffer
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.RecyclerView
    import com.example.android.nadris.databinding.ItemRvSubTeacherBinding
    import dataRvsubTeach

class customAdapterRVsubTeacher():
        RecyclerView.Adapter<customAdapterRVsubTeacher.viweholder>() {

    private val differCalback=object :DiffUtil.ItemCallback<dataRvsubTeach>(){
        override fun areItemsTheSame(oldItem: dataRvsubTeach, newItem: dataRvsubTeach): Boolean {
         return oldItem.id==newItem.id
        }
        override fun areContentsTheSame(oldItem: dataRvsubTeach, newItem: dataRvsubTeach): Boolean {
            return oldItem==newItem
        }
    }
        val differ =AsyncListDiffer(this,differCalback)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
        ): viweholder {
          val  v=ItemRvSubTeacherBinding.inflate(
              LayoutInflater.from(parent.context),
              parent,false)
            return viweholder(v)
        }
        override fun getItemCount(): Int {
            return  differ.currentList.size
        }
        override fun onBindViewHolder(holder: viweholder, position: Int) {
            val data: dataRvsubTeach =differ.currentList[position]
            holder.binding.tvNameSubjectTeacher.text  =data.name_subject_teacher
            holder.binding.tvCountStudentTeach.text  =data.count_student_teach.toString()
            holder.binding.tvClass.text =data.class_
            holder.binding.tvSemester.text =data.semester
            holder.binding.imgSubTeach.setImageResource(data.imag)
        }
        class viweholder (var binding:ItemRvSubTeacherBinding):RecyclerView.ViewHolder(binding.root){
         }
    }
