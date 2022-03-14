package com.example.android.nadris.ui.teacherActivity.subjects_teacher
/**
 * @author mohammed M sarhan
 * **/

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSubTeacherRvBinding
import com.example.android.nadris.ui.teacherActivity.choosingNewSubjects.customAdapterRVsubTeacher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubTeacherRVFragment : Fragment() {

    private val viewModel: SubTeacherRvViewModel by viewModels()
    private lateinit var adapter: customAdapterRVsubTeacher
    private lateinit var binding:FragmentSubTeacherRvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.fragment_sub_teacher_rv, container, false)
        binding =FragmentSubTeacherRvBinding.inflate(inflater)

        binding.viewmodel = viewModel
        viewModel.getdata()
        setupRV()
        binding.fabAddSubject.setOnClickListener {
            val action = SubTeacherRVFragmentDirections.actionSubTeacherRvFragmentToChoosingNewSubjects()
            findNavController().navigate(action)
        }

        return binding.root
    }


    private fun setupRV(){
        adapter= customAdapterRVsubTeacher()
        binding.rvSubjectTeacher.layoutManager=
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,false)
        binding.rvSubjectTeacher.adapter= adapter

        activity?.let {
            viewModel.list.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }

        }

    }


}