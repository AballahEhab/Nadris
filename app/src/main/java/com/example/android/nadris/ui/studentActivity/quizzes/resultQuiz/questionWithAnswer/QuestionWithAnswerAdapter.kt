package com.example.android.nadris.ui.studentActivity.quizzes.resultQuiz.questionWithAnswer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ItemQuestionWithAnswerBinding
import com.example.android.nadris.ui.teacherActivity.quiz_teacher.QuizData


class QuestionWithAnswerAdapter(context: Context):RecyclerView.Adapter<QuestionWithAnswerAdapter.ViewHolder>() {
var context = context
    private val differCallback = object : DiffUtil.ItemCallback<QuizData>(){
        override fun areItemsTheSame(oldItem: QuizData, newItem: QuizData): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: QuizData, newItem: QuizData): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): QuestionWithAnswerAdapter.ViewHolder {
        val v = ItemQuestionWithAnswerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        val num = position+1
        holder.binding.QuestionText.text = num.toString() +"-"+data.question
        holder.binding.textAnswerOne.text = "1- "+data.answer[0]
        holder.binding.textAnswerTwo.text = "2- "+data.answer[1]
        holder.binding.textAnswerThree.text = "3- "+data.answer[2]
        holder.binding.textAnswerFour.text = "4- "+data.answer[3]
        holder.binding.locationText.text = data.answer_location
        if(data.correct_ans.contains(1)){
            holder.binding.textAnswerOne.setTextColor(ContextCompat.getColor(context,R.color.green) )
        }
        if(data.correct_ans.contains(2)){
            holder.binding.textAnswerTwo.setTextColor(ContextCompat.getColor(context,R.color.green) )
        }
        if(data.correct_ans.contains(3)){
            holder.binding.textAnswerThree.setTextColor(ContextCompat.getColor(context,R.color.green) )
        }
        if(data.correct_ans.contains(4)){
            holder.binding.textAnswerFour.setTextColor(ContextCompat.getColor(context,R.color.green) )
        }
    }




    class ViewHolder(var binding: ItemQuestionWithAnswerBinding) : RecyclerView.ViewHolder(binding.root) {
    }


}