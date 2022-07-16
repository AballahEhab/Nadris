package com.example.android.nadris.ui.studentActivity.quizzes

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.nadris.R
import com.example.android.nadris.databinding.QuizFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizFragment : Fragment() {
    val viewModel: QuizViewModel by viewModels()
    private lateinit var binding: QuizFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.quiz_fragment, container, false)
        binding = QuizFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        viewModel.questionList = setData.getQuestion()
        setQuestion()
        binding.opt1.setOnClickListener{
            selectedOptionStyle(binding.opt1,1)
        }
        binding.opt2.setOnClickListener{
            selectedOptionStyle(binding.opt2,2)
        }
        binding.opt3.setOnClickListener{
            selectedOptionStyle(binding.opt3,3)
        }
        binding.opt4.setOnClickListener{
            selectedOptionStyle(binding.opt4,4)
        }
        binding.submit.setOnClickListener{
            if(viewModel.selecedOption!=0){
                val question=viewModel.questionList!![viewModel.currentPosition-1]
                if(viewModel.selecedOption!=question.correct_ans)
                {
                    setColor(viewModel.selecedOption,R.drawable.wrong_question_option)
                }else{
                    viewModel.score++;
                }
                setColor(question.correct_ans,R.drawable.correct_question_option)
                if(viewModel.currentPosition==viewModel.questionList!!.size){
                    binding.submit.text="FINISH"}
                else{
                    binding.submit.text="Go to Next"}
            }else{
                viewModel.currentPosition++
                when{
                    viewModel.currentPosition<=viewModel.questionList!!.size->{
                        setQuestion()
                    }
                }
            }
            viewModel.selecedOption=0
        }
        return binding.root
    }

    fun setQuestion() {

        val question = viewModel.questionList!![viewModel.currentPosition - 1]
        setOptionStyle()
        binding.progressBar.progress = viewModel.currentPosition
        binding.progressBar.max = viewModel.questionList!!.size
        binding.progressText.text = "${viewModel.currentPosition}" + "/" + "${viewModel.questionList!!.size}"
        binding.questionText.text = question.question
        binding.opt1.text = question.option_one
        binding.opt2.text = question.option_tw0
        binding.opt3.text = question.option_three
        binding.opt4.text = question.option_four




    }
    //
//
//
    fun setColor(opt: Int, color: Int) {
        when (opt) {
            1 -> {
                binding.opt1.setBackgroundResource(color)
            }
            2 -> {
                binding.opt2.setBackgroundResource(color)
            }
            3 -> {
                binding.opt3.setBackgroundResource(color)
            }
            4 -> {
                binding.opt4.setBackgroundResource(color)
            }
        }
    }

    fun setOptionStyle() {
//        binding.opt1.setBackgroundResource(R.drawable.question_option)
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, binding.opt1)
        optionList.add(1, binding.opt2)
        optionList.add(2, binding.opt3)
        optionList.add(3, binding.opt4)

        for (op in optionList) {
            op.setTextColor(Color.parseColor("#555151"))
            op.setBackgroundResource(R.drawable.question_option)
            op.typeface = Typeface.DEFAULT
        }
    }

    fun selectedOptionStyle(view: TextView, opt: Int) {
        setOptionStyle()
        viewModel.selecedOption = opt
        view.setBackgroundResource( R.drawable.selected_question_option)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))
    }


}