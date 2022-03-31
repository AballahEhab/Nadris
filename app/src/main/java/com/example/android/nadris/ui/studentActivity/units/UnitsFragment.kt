package com.example.android.nadris.ui.studentActivity.units

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentUnitsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UnitsFragment : Fragment() {
    val args: UnitsFragmentArgs by navArgs()
    val viewModel : UnitsViewModel by  viewModels()
    private lateinit var binding: FragmentUnitsBinding
    private lateinit var adapter: UnitItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_units, container, false)
        binding = FragmentUnitsBinding.inflate(inflater)
        viewModel.subjectId= args.subjectId
        binding.viewmodel = viewModel
        viewModel.getdata()
        setupRV()


//        val dataList = listOf(
//            SubjectUnit(1,
//                "unit1",
//                listOf(Lesson(1, "lesson1"),
//                    Lesson(2, "lesson2"),
//                    Lesson(3, "lesson3"),
//                    Lesson(4, "lesson4")),
//                R.drawable.ic_launcher_background),
//        )



        return binding.root
    }
    private fun setupRV(){
        adapter= UnitItemAdapter(viewModel)
        binding.unitsRv.layoutManager=
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,false)
        binding.unitsRv.adapter= adapter

        activity?.let {
            viewModel.list.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }

        }

    }
}