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
import androidx.recyclerview.widget.RecyclerView

class customAdapterRVTeach(var teacherList:ArrayList<dataRvTeach>)
    :RecyclerView.Adapter<customAdapterRVTeach.Viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_teachers,parent,false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val data:dataRvTeach=teacherList[position]

        holder.my_name_teacher.text=data.name_teachers
        holder.my_content_description.text=data.content_description
        holder.my_evaluation.text=data.evaluation.toString()
        holder.my_teachers_photo.setImageResource(data.iv_teachers_photo)

         holder.my_data=data  //to send the valu data to veiw holder

    }

    override fun getItemCount(): Int {
        return teacherList.size
    }

class Viewholder(itemViewt :View,var my_data:dataRvTeach?=null):RecyclerView.ViewHolder(itemViewt){
    init {
        itemView.setOnClickListener {
            Toast.makeText(itemView.context, my_data?.name_teachers, Toast.LENGTH_LONG).show()
        }
    }

         var my_name_teacher=itemViewt.findViewById(R.id.tv_name_teachers)  as TextView
         var my_content_description=itemViewt.findViewById(R.id.tv_content_description)  as TextView
         var my_evaluation=itemViewt.findViewById(R.id.tv_evaluation)  as TextView
         var my_teachers_photo=itemViewt.findViewById(R.id.iv_teachers)  as ImageView

}


}
data class dataRvTeach(var name_teachers : String,
                var content_description:String,
                var evaluation:Int,
                var iv_teachers_photo:Int)
