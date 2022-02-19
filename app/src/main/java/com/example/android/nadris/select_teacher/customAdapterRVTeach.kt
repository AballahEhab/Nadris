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
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.ItemRvTeachersBinding
//var teacherList:ArrayList<dataRvTeach>
class customAdapterRVTeach()
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
         //holder.my_data=data  //to send the valu data to veiw holder

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

class Viewholder(var binding:ItemRvTeachersBinding):RecyclerView.ViewHolder(binding.root){
//    init {
//        itemView.setOnClickListener {
//            //,var my_data:dataRvTeach?=null
//           // Toast.makeText(itemView.context, my_data?.name_teachers, Toast.LENGTH_LONG).show()
//        }
//    }
    /*
    data binding in item
    used in view holder
    used in on bindingHolder
    change onCreateViewHolder to use binding
    difutil
    AsyncListDiffer<data>
    used differ in onBindViewHolder & getItemCount
   */



//         var my_name_teacher=itemViewt.findViewById(R.id.tv_name_teachers)  as TextView
//         var my_content_description=itemViewt.findViewById(R.id.tv_content_description)  as TextView
//         var my_evaluation=itemViewt.findViewById(R.id.tv_evaluation)  as TextView
//         var my_teachers_photo=itemViewt.findViewById(R.id.iv_teachers)  as ImageView

}


}
data class dataRvTeach(var id:Int,
                var name_teachers : String,
                var evaluation:Int,
                var iv_teachers_photo:Int)
