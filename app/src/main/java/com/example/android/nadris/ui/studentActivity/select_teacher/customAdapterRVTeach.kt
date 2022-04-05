package com.example.android.nadris
/**
 * @author mohammed sarhan
 * **/
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.ItemRvTeachersBinding
import com.example.android.nadris.ui.studentActivity.select_teacher.teachers_RV_fragmentDirections

//var teacherList:ArrayList<dataRvTeach>
class customAdapterRVTeach
    :RecyclerView.Adapter<customAdapterRVTeach.Viewholder>() {

    private val differCallback=object :DiffUtil.ItemCallback<dataRvTeach>(){
        override fun areItemsTheSame(oldItem: dataRvTeach, newItem: dataRvTeach): Boolean {
          return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: dataRvTeach, newItem: dataRvTeach): Boolean {
            return oldItem == newItem
        }
    }
//computing different bettween two list
    val differ =AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : Viewholder {
//        val v = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_rv_teachers,parent,false)
        val v =ItemRvTeachersBinding.inflate(LayoutInflater.
        from(parent.context),
            parent,false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val data:dataRvTeach=differ.currentList[position]

        holder.binding.tvNameTeachers.text=data.name_teachers
        holder.binding.tvEvaluation.text=data.evaluation.toString()
        holder.binding.ivTeachers.setImageResource(data.iv_teachers_photo)

        holder.binding.tvNameTeachers.setOnClickListener {
            var action =teachers_RV_fragmentDirections.actionTeachersRVFragmentToHeadlineSubjects()
            it.findNavController().navigate(action)
        }




    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

class Viewholder(var binding:ItemRvTeachersBinding):RecyclerView.ViewHolder(binding.root)


}
data class dataRvTeach(var id:Int,
                var name_teachers : String,
                var evaluation:Int,
                var iv_teachers_photo:Int)
