package com.example.android.nadris.ui.studentActivity.subject_student.exploreCourses

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSubjectsRvBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectsRvFragment : Fragment() {

    val TAG = "SubjectsRvFragment"
      val viewModel: SubjectsRvFragmentViewModel by viewModels()
    private lateinit var binding:FragmentSubjectsRvBinding
    private lateinit var adapter: customAdapterRVsub

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

     inflater.inflate(R.layout.fragment_subjects_rv, container, false)
        binding = FragmentSubjectsRvBinding.inflate(inflater)

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
            viewModel.subjectsList.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }
        }

        viewModel.subjectResult.observe(viewLifecycleOwner){result->
            result?.handleRepoResponse(
                onPreExecute = {
//                    binding.swipeRefreshLayout.isRefreshing = false

                },
                onLoading = {
//                    binding.swipeRefreshLayout.isRefreshing = true
//                    result.data?.let {
//                        adapter.differ.submitList(it)
//                        binding.swipeRefreshLayout.isRefreshing = false
//                    }
                },
                onError = {
                    Snackbar.make(binding.root, result.error!!, Snackbar.LENGTH_LONG)
                        .show()
                },
                onSuccess = {
                    Log.v(TAG, result.data.toString())
                    viewModel.subjectsList.value = result.data

                }
            )
        }


    }

}