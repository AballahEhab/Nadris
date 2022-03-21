package com.example.android.nadris.ui.studentActivity.settings.help
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding
     val viewModel: HelpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_help, container, false)
        binding = FragmentHelpBinding.inflate(inflater)
        binding.viewmodel=viewModel

        binding.imagBackSetting.setOnClickListener {
            this.findNavController()
                .navigate(HelpFragmentDirections.actionHelpFragmentToSettingsFragment())
        }


        return binding.root
    }



}