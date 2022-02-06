package com.example.android.nadris.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R

class customAdapter (var postList:ArrayList<dataRvPost>)
    :RecyclerView.Adapter<customAdapter.Viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardcell,parent,false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val data: dataRvPost =postList[position]

        holder.imageStudent.setImageResource(data.imageStudent)
        holder.studentName.text=data.studentName
        holder.subjectName.text=data.subjectName
        holder.post_text.text=data.post_text

        holder.my_data=data  //to send the valu data to veiw holder

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class Viewholder(itemViewt :View,var my_data: dataRvPost?=null):RecyclerView.ViewHolder(itemViewt){
//        init {
//            Toast.makeText(itemView.context,my_data?.studentName, Toast.LENGTH_LONG).show()
//
//        }

        var imageStudent=itemViewt.findViewById(R.id.profileImage)  as ImageView
        var studentName=itemViewt.findViewById(R.id.textViewAccountName)  as TextView
        var subjectName=itemViewt.findViewById(R.id.textSubjectName)  as TextView
        var post_text=itemViewt.findViewById(R.id.textViewPost)  as TextView

    }


}
data class dataRvPost(
    var imageStudent : Int,
    var studentName:String,
    var subjectName:String,
    var post_text:String, )
