package com.example.android.nadris.subTeacherRv
/**
 * @author mohammed M sarhan
 * **/

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.customAdapterRVsubTeacher
import com.example.android.nadris.databinding.SubTeacherRvFragmentBinding

class sub_teacher_rv_Fragment : Fragment() {



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



        setupRV();
        binding.fabAddSubject.setOnClickListener {
            //todo Respond to Extended FAB click
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