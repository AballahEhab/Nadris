package com.example.android.nadris.ui.studentActivity.subject_student
/**
 * @author mohammed M sarhan
 * **/
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.customAdapterRVsub
import com.example.android.nadris.databinding.FragmentSubjectsRvBinding

class SubjectsRvFragment : Fragment() {

    private lateinit var viewModel: SubjectsRvFragmentViewModel
    private lateinit var binding:FragmentSubjectsRvBinding
    private lateinit var adapter: customAdapterRVsub
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

     inflater.inflate(R.layout.fragment_subjects_rv, container, false)
        binding=FragmentSubjectsRvBinding.inflate(inflater)

        viewModel =ViewModelProvider(this).get(SubjectsRvFragmentViewModel::class.java)
       binding.viewmodel = viewModel

        setupRV()

        return binding.root
    }
    private fun setupRV(){
        adapter= customAdapterRVsub()
        binding.RVSubjects.layoutManager=
            LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL,false)
        binding.RVSubjects.adapter= adapter

        activity?.let {
            viewModel.getdata().observe(
                viewLifecycleOwner
            ) {
                adapter.differ.submitList(it)
            }

        }


    }



}