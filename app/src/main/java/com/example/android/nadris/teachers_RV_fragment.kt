package com.example.android.nadris

/**
 * @author mohammed sarhan
 * **/
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.SubjectsRvFragmentBinding
import com.example.android.nadris.databinding.TeachersRVFragmentBinding

class teachers_RV_fragment : Fragment() {


    private lateinit var viewModel: TeachersRVFragmentViewModel
    private lateinit var adapter: customAdapterRVTeach
    private lateinit var binding: TeachersRVFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inflater.inflate(R.layout.teachers__r_v_fragment, container, false)
        binding = TeachersRVFragmentBinding.inflate(inflater);

        viewModel = ViewModelProvider(this).get(TeachersRVFragmentViewModel::class.java)
        binding.viewmodel = viewModel

        setupRV()
//        binding.RVTeachers.layoutManager=
//            LinearLayoutManager(requireContext(),
//            RecyclerView.VERTICAL,false)
//        binding.RVTeachers.adapter= customAdapterRVTeach()

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
            viewModel.getdata().observe(
                viewLifecycleOwner, {
                    adapter.differ.submitList(it)
                }
            )

        }
    }


}