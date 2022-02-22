package com.example.android.nadris
/**
 * @author mohammed sarhan
 * **/
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.database.DatabaseSubject
import com.example.android.nadris.databinding.ItemRvSubjectsBinding
import com.example.android.nadris.ui.studentActivity.subject_student.SubjectsRvFragmentDirections

class customAdapterRVsub()
    : RecyclerView.Adapter<customAdapterRVsub.ViewHolder>() {

    private val differCallback=object : DiffUtil.ItemCallback<DatabaseSubject>(){
        override fun areItemsTheSame(oldItem: DatabaseSubject, newItem: DatabaseSubject): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: DatabaseSubject, newItem: DatabaseSubject): Boolean {
            return oldItem==newItem
        }

    }

     val differ=AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        var v = ItemRvSubjectsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: DatabaseSubject = differ.currentList[position]

        holder.binding.tvNameSubject.text = data.name_subject
        holder.binding.tvCountTeacher.text = data.count_teach.toString()
        holder.binding.ivSubjects.setImageResource(data.imv_subjects)
        holder.itemView.setOnClickListener {
            var action = SubjectsRvFragmentDirections.actionSubjectsRvFragmentToTeachersRVFragment()
            it.findNavController().navigate(action)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(var binding: ItemRvSubjectsBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
//data class DatabaseSubject(var id:Int,
//                           var name_subject:String,
//                           var count_teach:Int,
//                           var imv_subjects:Int )