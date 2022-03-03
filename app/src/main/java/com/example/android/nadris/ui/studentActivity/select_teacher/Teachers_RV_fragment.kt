package com.example.android.nadris.ui.studentActivity.select_teacher

/**
 * @author mohammed sarhan
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
import com.example.android.nadris.customAdapterRVTeach
import com.example.android.nadris.databinding.FragmentTeachersRVBinding

class teachers_RV_fragment : Fragment() {


    private lateinit var viewModel: TeachersRVFragmentViewModel
    private lateinit var adapter: customAdapterRVTeach
    private lateinit var binding: FragmentTeachersRVBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflater.inflate(R.layout.fragment_teachers__r_v, container, false)
        binding = FragmentTeachersRVBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(TeachersRVFragmentViewModel::class.java)
        binding.viewmodel = viewModel

        setupRV()
        return binding.root
    }

    private fun setupRV() {

        adapter = customAdapterRVTeach()
        binding.RVTeachers.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false
            )
        binding.RVTeachers.adapter = adapter

        activity?.let {
            viewModel.getdata().observe(//observe=>  acss on data return from getdata()
                // LiveData a where Lifecyc
                viewLifecycleOwner
            ) {
                adapter.differ.submitList(it) //pass data when change to diffutil
            }

        }
    }


}