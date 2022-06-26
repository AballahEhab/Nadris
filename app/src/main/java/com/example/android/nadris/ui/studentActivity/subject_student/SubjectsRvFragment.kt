package com.example.android.nadris.ui.studentActivity.subject_student
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSubjectsRvBinding
import com.example.android.nadris.ui.customAdapterRVsub
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class SubjectsRvFragment : Fragment() {

      val viewModel:SubjectsRvFragmentViewModel by viewModels()
    private lateinit var binding:FragmentSubjectsRvBinding
    private lateinit var adapter: customAdapterRVsub
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

     inflater.inflate(R.layout.fragment_subjects_rv, container, false)
        binding=FragmentSubjectsRvBinding.inflate(inflater)

       binding.viewmodel = viewModel
        viewModel.getData()
        setupRV()
        return binding.root
    }
    private fun setupRV(){
        adapter= customAdapterRVsub()
        binding.RVSubjects.layoutManager=
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,false)
        binding.RVSubjects.adapter= adapter

        activity?.let {
//            viewModel.list.observe(viewLifecycleOwner) {
//                adapter.differ.submitList(it)
//            }
        }


    }



}