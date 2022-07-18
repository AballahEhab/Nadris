package com.example.android.nadris.ui.studentActivity.quizzes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.QuizFragmentBinding
import com.example.android.nadris.ui.teacherActivity.quiz_teacher.TeacherQuizFragmentDirections
import com.example.android.nadris.util.isVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {
    val TAG = "StudentQuizFragment"
    val viewModel: QuizViewModel by activityViewModels()
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
        binding.CBAnswer1.setOnCheckedChangeListener { buttonView, isChecked ->
            val checked1: Boolean = binding.CBAnswer1.isChecked
            if (checked1) {
                viewModel.selecedOption.add(1)
            } else {
                viewModel.selecedOption.remove(1)
            }
        }
        binding.CBAnswer2.setOnCheckedChangeListener { buttonView, isChecked ->
            val checked2: Boolean = binding.CBAnswer2.isChecked
            if (checked2) {
                viewModel.selecedOption.add(2)
            } else {
                viewModel.selecedOption.remove(2)
            }
        }
        binding.CBAnswer3.setOnCheckedChangeListener { buttonView, isChecked ->
            val checked3: Boolean = binding.CBAnswer3.isChecked
            if (checked3) {
                viewModel.selecedOption.add(3)
            } else {
                viewModel.selecedOption.remove(3)
            }
        }
        binding.CBAnswer4.setOnCheckedChangeListener { buttonView, isChecked ->
            val checked4: Boolean = binding.CBAnswer4.isChecked
            if (checked4) {
                viewModel.selecedOption.add(4)
            } else {
                viewModel.selecedOption.remove(4)
            }
        }
        if(binding.submit.text=="FINISH"){
            binding.submit.setOnClickListener{
                val action = QuizFragmentDirections.actionStudentQuizToResultQuiz()
                findNavController().navigate(action)
            }
        }

        return binding.root
    }

    fun setQuestion() {

        val question = viewModel.questionList!![viewModel.currentPosition - 1]
        binding.progressBar.progress = viewModel.currentPosition
        binding.progressBar.max = viewModel.questionList!!.size
        binding.progressText.text = "${viewModel.currentPosition}" + "/" + "${viewModel.questionList!!.size}"
        binding.questionText.text = question.question
        binding.CBAnswer1.text = question.answer[0]
        binding.CBAnswer2.text = question.answer[1]
        binding.CBAnswer3.text = question.answer[2]
        binding.CBAnswer4.text = question.answer[3]
        binding.RBAnswer1.text = question.answer[0]
        binding.RBAnswer2.text = question.answer[1]
        binding.RBAnswer3.text = question.answer[2]
        binding.RBAnswer4.text = question.answer[3]
        if (question.correct_ans.size == 1) {
            binding.RBAnswer1.isVisible(true)
            binding.RBAnswer2.isVisible(true)
            binding.RBAnswer3.isVisible(true)
            binding.RBAnswer4.isVisible(true)
            binding.CBAnswer1.isVisible(false)
            binding.CBAnswer2.isVisible(false)
            binding.CBAnswer3.isVisible(false)
            binding.CBAnswer4.isVisible(false)

            binding.submit.setOnClickListener {
                when(binding.RBGroupAnswer.checkedRadioButtonId){
                    R.id.RB_answer1-> {
                        viewModel.selecedOption.clear()
                        viewModel.selecedOption.add(1)
                    }
                    R.id.RB_answer2->{
                        viewModel.selecedOption.clear()
                        viewModel.selecedOption.add(2)
                        Log.v(TAG,"RB_answer2 CLICKED")
                    }
                    R.id.RB_answer3->{
                        viewModel.selecedOption.clear()
                        viewModel.selecedOption.add(3)
                    }
                    R.id.RB_answer4->{
                        viewModel.selecedOption.clear()
                        viewModel.selecedOption.add(4)
                    }
                }
                if (viewModel.selecedOption.containsAll(question.correct_ans)) {
                    viewModel.score++
                } else {
                    viewModel.wrongQuestion.add(viewModel.currentPosition)
                }
                ++viewModel.currentPosition

                if(viewModel.currentPosition > viewModel.questionList!!.size){

                    val action = QuizFragmentDirections.actionStudentQuizToResultQuiz()
                    findNavController().navigate(action) }

                binding.RBAnswer1.isChecked = false
                binding.RBAnswer2.isChecked = false
                binding.RBAnswer3.isChecked = false
                binding.RBAnswer4.isChecked = false
                if (viewModel.currentPosition == viewModel.questionList!!.size) {
                    binding.submit.text = "FINISH"

                } else {
                    binding.submit.text = "Go to Next"
                }
                when {
                    viewModel.currentPosition <= viewModel.questionList!!.size -> {
                        setQuestion()
                    }
                }
            }
        } else {
            binding.RBAnswer1.isVisible(false)
            binding.RBAnswer2.isVisible(false)
            binding.RBAnswer3.isVisible(false)
            binding.RBAnswer4.isVisible(false)
            binding.CBAnswer1.isVisible(true)
            binding.CBAnswer2.isVisible(true)
            binding.CBAnswer3.isVisible(true)
            binding.CBAnswer4.isVisible(true)
            binding.submit.setOnClickListener {
                //
                if (viewModel.selecedOption.containsAll(question.correct_ans)) {
                    viewModel.score++
                } else {
                    viewModel.wrongQuestion.add(viewModel.currentPosition)

                }
                ++viewModel.currentPosition

                if(viewModel.currentPosition > viewModel.questionList!!.size){

                    val action = QuizFragmentDirections.actionStudentQuizToResultQuiz()
                    findNavController().navigate(action) }



                binding.CBAnswer1.isChecked = false
                binding.CBAnswer2.isChecked = false
                binding.CBAnswer3.isChecked = false
                binding.CBAnswer4.isChecked = false




                if (viewModel.currentPosition == viewModel.questionList!!.size) {
                    binding.submit.text = "FINISH"
                } else {
                    binding.submit.text = "Go to Next"
                }


                    if(viewModel.currentPosition <= viewModel.questionList!!.size )
                        setQuestion()


            }
        }


    }}


