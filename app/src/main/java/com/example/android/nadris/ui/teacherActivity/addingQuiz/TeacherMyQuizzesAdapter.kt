package com.example.android.nadris.ui.teacherActivity.addingQuiz


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ItemQuizCardBinding
import com.example.android.nadris.network.firebase.dtos.QuizData

class TeacherMyQuizzesAdapter: RecyclerView.Adapter<TeacherMyQuizzesAdapter.ViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<QuizData>() {
        override fun areItemsTheSame(oldItem: QuizData, newItem: QuizData): Boolean {
            return oldItem.quizId == newItem.quizId
        }

        override fun areContentsTheSame(oldItem: QuizData, newItem: QuizData): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): ViewHolder {
        val v = ItemQuizCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val data = differ.currentList[position]

        holder.binding.tvTitleQuiz.text = data.quizTitle
        holder.binding.numOfQuestion.text = data.questions.size.toString()
        holder.binding.teacherName.text = data.teacherName
        holder.binding.tvClass.text = data.gradeName
        holder.binding.tvSemester.text =
            if (data.term) holder.binding.root.resources.getString(R.string.first_term)
            else holder.binding.root.resources.getString(R.string.second_term)

        holder.binding.root.setOnClickListener {
                it.findNavController().navigate(listOfQuizzesFragmentDirections.actionQuizzesFragmentToQuiz())
        }
    }

    class ViewHolder(var binding: ItemQuizCardBinding) : RecyclerView.ViewHolder(binding.root)

}