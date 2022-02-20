package com.example.android.nadris.ui.teacherActivity.subjects_teacher
/**
 * @author mohammed M sarhan
 * **/

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.ui.teacherActivity.choosingNewSubjects.customAdapterRVsubTeacher
import com.example.android.nadris.databinding.SubTeacherRvFragmentBinding

class sub_teacher_rv_Fragment : Fragment() {


    private lateinit var args:sub_teacher_rv_FragmentArgs
    private lateinit var viewModel: SubTeacherRvViewModel
    private lateinit var adapter: customAdapterRVsubTeacher
    private lateinit var binding:SubTeacherRvFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inflater.inflate(R.layout.sub_teacher_rv__fragment, container, false)
         binding =SubTeacherRvFragmentBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(SubTeacherRvViewModel::class.java)
        binding.viewmodel = viewModel

        //retriv data frim choosing
         viewModel.select_class=args.selectClass
        viewModel.select_subject=args.selectSubject
        viewModel.select_semester= args.selectSemester.toString()

        setupRV();
        binding.fabAddSubject.setOnClickListener {
            var action = sub_teacher_rv_FragmentDirections.actionSubTeacherRvFragmentToChoosingNewSubjects()
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
            viewModel.getdata().observe(
                viewLifecycleOwner, {
                    adapter.differ.submitList(it)
                }
            )

        }

    }


}