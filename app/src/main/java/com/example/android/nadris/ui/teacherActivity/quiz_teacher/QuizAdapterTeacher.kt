package com.example.android.nadris.ui.teacherActivity.quiz_teacher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.QuizItemBinding

class QuizAdapterTeacher(val viewModel: TeacherQuizViewModel) : RecyclerView.Adapter<QuizAdapterTeacher.ViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<QuizData>() {
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
    ): ViewHolder {
        val v = QuizItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val data = differ.currentList[position]
        holder.binding.editTextQuestion.setText(data.question)
        holder.binding.textAnswerOne.setText(data.answer[0])
        holder.binding.textAnswerTwo.setText(data.answer[1])
        holder.binding.textAnswerThree.setText(data.answer[2])
        holder.binding.textAnswerFour.setText(data.answer[3])
        if(data.correct_ans.contains(1)){
            holder.binding.answer1.isChecked=true
        }
        if (data.correct_ans.contains(2)){
            holder.binding.answer2.isChecked=true}
       if (data.correct_ans.contains(3)){
            holder.binding.answer3.isChecked=true}
         if (data.correct_ans.contains(4)){
            holder.binding.answer4.isChecked=true}
        


        holder.binding.editTextQuestion.doOnTextChanged { text, start, before, count ->
            viewModel.list.value!![position].question = text.toString()
        }
        holder.binding.textAnswerOne.doOnTextChanged { text, start, before, count ->
            viewModel.list.value!![position].answer.set(0,text.toString())
        }

        holder.binding.textAnswerTwo.doOnTextChanged { text, start, before, count ->
            viewModel.list.value!![position].answer.set(1,text.toString())
        }
        holder.binding.textAnswerThree.doOnTextChanged { text, start, before, count ->
            viewModel.list.value!![position].answer.set(2,text.toString())
        }
        holder.binding.textAnswerFour.doOnTextChanged { text, start, before, count ->
            viewModel.list.value!![position].answer.set(3,text.toString())
        }

        holder.binding.answer1.setOnCheckedChangeListener { buttonView, isChecked ->
            val checked1: Boolean = holder.binding.answer1.isChecked

        if(checked1 ){
            viewModel.list.value!![position].correct_ans.add(1)}
        else{
            viewModel.list.value!![position].correct_ans.remove(1)
        }
        }
        holder.binding.answer2.setOnCheckedChangeListener { buttonView, isChecked ->
            val checked2: Boolean = holder.binding.answer2.isChecked

            if(checked2 ){
                viewModel.list.value!![position].correct_ans.add(2)}
            else{
                viewModel.list.value!![position].correct_ans.remove(2)
            }
        }
        holder.binding.answer3.setOnCheckedChangeListener { buttonView, isChecked ->
            val checked3: Boolean = holder.binding.answer3.isChecked

            if(checked3 ){
                viewModel.list.value!![position].correct_ans.add(3)}
            else{
                viewModel.list.value!![position].correct_ans.remove(3)
            }
        }
        holder.binding.answer4.setOnCheckedChangeListener { buttonView, isChecked ->
            val checked4: Boolean = holder.binding.answer4.isChecked

            if(checked4 ){
                viewModel.list.value!![position].correct_ans.add(4)}
            else{
                viewModel.list.value!![position].correct_ans.remove(4)
            }
        }



    }



    class ViewHolder(var binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }


}