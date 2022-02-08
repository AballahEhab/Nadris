package com.example.android.nadris


    import android.content.Intent
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import android.widget.Toast
    import androidx.recyclerview.widget.RecyclerView

    class customAdapterRVsubTeacher(val subjectList:ArrayList<dataRvsubTeach>):
        RecyclerView.Adapter<customAdapterRVsubTeacher.viweholder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
        ): customAdapterRVsubTeacher.viweholder {
            val v =LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sub_teacher,parent,false)
        return viweholder(v)
        }
        override fun getItemCount(): Int {
            return  subjectList.size
        }
        override fun onBindViewHolder(holder: customAdapterRVsubTeacher.viweholder, position: Int) {
            val data:dataRvsubTeach=subjectList[position]

            holder.tv_name_subject.text =data.name_subject_teacher
            holder.tv_count_studen.text =data.count_student_teach.toString()
            holder.tv_class.text =data.class_
            holder.tv_semester.text =data.semester
            holder.img_sub_teach.setImageResource(data.imag)
            holder.mydata=data
        }

        class viweholder (itemview :View ,var mydata:dataRvsubTeach?=null):RecyclerView.ViewHolder(itemview){
            init {
                itemview.setOnClickListener {
                    Toast.makeText(itemview.context, mydata?.name_subject_teacher, Toast.LENGTH_SHORT).show()
                   // var myintent =Intent(itemview.context,//nam class will  go to ::class.java)
//                    myintent.putExtra("key",mydata?.name_subject_teacher)
//                    itemview.context.startActivity(myintent)
//
                }
            }
            val tv_name_subject = itemView.findViewById(R.id.tv_name_subject_teacher) as TextView
            val tv_count_studen = itemView.findViewById(R.id.tv_count_student_teach) as TextView
            val tv_class = itemView.findViewById(R.id.tv_class) as TextView
            val tv_semester = itemView.findViewById(R.id.tv_semester) as TextView
            val img_sub_teach = itemView.findViewById(R.id.img_sub_teach) as ImageView
        }
}
data class dataRvsubTeach(var name_subject_teacher : String,
                          var count_student_teach  :Int,
                          var class_  :String,
                          var semester:String,
                          var imag    :Int)