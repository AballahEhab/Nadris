package com.example.android.nadris.ui.studentActivity.subject_student.mySubject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSubTeacherRvBinding
import com.example.android.nadris.databinding.MySubjectStudentFragmentBinding
import com.example.android.nadris.ui.teacherActivity.choosingNewSubjects.SubjectAdapter
import com.example.android.nadris.ui.teacherActivity.subjects_teacher.SubjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MySubjectStudentFragment : Fragment() {
    private val viewModel: MySubjectStudentViewModel by viewModels()
    private lateinit var adapter: MySubjectAdapter
    private lateinit var binding: MySubjectStudentFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
         inflater.inflate(R.layout.my_subject_student_fragment, container, false)
        binding = MySubjectStudentFragmentBinding.inflate(inflater)
        binding.viewmodel = viewModel
        viewModel.getdata()
        setupRV()
        return binding.root
    }
    private fun setupRV(){
        adapter = MySubjectAdapter()
        binding.rvSubjectStudent.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false)
        binding.rvSubjectStudent.adapter= adapter

        activity?.let {
            viewModel.list.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }

        }

    }


}