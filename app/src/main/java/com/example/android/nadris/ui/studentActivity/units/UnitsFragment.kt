package com.example.android.nadris.ui.studentActivity.units

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentUnitsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnitsFragment : Fragment() {
    val args: UnitsFragmentArgs by navArgs()
    val viewModel: UnitsViewModel by viewModels()
    private lateinit var binding: FragmentUnitsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_units, container, false)
        binding = FragmentUnitsBinding.inflate(inflater)

        binding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.subjectId = args.subjectId
        binding.viewmodel = viewModel
        viewModel.getData()
        binding.adapter = UnitItemAdapter(viewModel)

        viewModel.units.observe(viewLifecycleOwner) {
            binding.adapter!!.differ.submitList(it)
        }

        return binding.root
    }

}