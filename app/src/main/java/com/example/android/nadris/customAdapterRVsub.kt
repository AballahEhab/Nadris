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

class customAdapterRVsub(val subjectList:ArrayList<dataRVsubITEM>)
    : RecyclerView.Adapter<customAdapterRVsub.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int
    ): ViewHolder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_teachers,parent,false)

        var v =LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_subjects,parent,false)

        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data:dataRVsubITEM=subjectList[position]

        holder.my_name_subject.text=data.name_subject
        holder.my_count_student.text=data.count_student.toString()
        holder.my_count_teacher.text=data.count_teach.toString()
        holder.my_iv_subjects.setImageResource(data.imv_subjects)
       holder.my_data=data  //to send the valu data to veiw holder
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    class ViewHolder(itemView:View ,var my_data:dataRVsubITEM?=null):RecyclerView.ViewHolder(itemView){
       init {
           itemView.setOnClickListener{
                Toast.makeText(itemView.context,my_data?.name_subject,Toast.LENGTH_LONG).show()
            }
        }
        var my_name_subject=itemView.findViewById(R.id.tv_name_subject) as TextView
        var my_count_student=itemView.findViewById(R.id.tv_count_student) as TextView
        var my_count_teacher=itemView.findViewById(R.id.tv_count_teacher)  as TextView
        var my_iv_subjects =itemView.findViewById(R.id.iv_subjects)  as ImageView

    }
}