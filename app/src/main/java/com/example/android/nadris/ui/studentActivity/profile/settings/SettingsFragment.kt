package com.example.android.nadris.ui.studentActivity.profile.settings

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSettingsBinding
import com.example.android.nadris.services.LocaleHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding:FragmentSettingsBinding
    val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_settings, container, false)
        binding = FragmentSettingsBinding.inflate(inflater)

        binding.backProfile.setOnClickListener {
            this.findNavController()
                .navigate(SettingsFragmentDirections.actionSettingsFragmentToPrivateProfileFragment())
        }


        binding.tvChangePassword.setOnClickListener {
            this.findNavController()
                .navigate(SettingsFragmentDirections.actionSettingsFragmentToChangePassword())

        }

        binding.tvChangeClass.setOnClickListener {
            // todo ChangeClass with api 
            showChangeClassOptionsDialog()
        }

        binding.tvNightMode.setOnClickListener {
            showThemOptionsDialog()
        }

        binding.tvChangeLanguage.setOnClickListener {
            showLanguageOptionsDialog()
        }

        binding.tvHelp.setOnClickListener {
            this.findNavController()
                .navigate(SettingsFragmentDirections.actionSettingsFragmentToHelpFragment())

        }


        return binding.root
    }


    private fun showLanguageOptionsDialog() {

        val dialogBuilder= MaterialAlertDialogBuilder(requireContext())
        var options :Array<out String>
        lateinit var onOptionsClick: (DialogInterface?, Int)-> Unit

        options = resources.getStringArray(R.array.LanguageOptions)
        onOptionsClick = { dialog, item ->
            when (options[item]) {
                options[0] -> LocaleHelper.setLocale(requireActivity(), "en")
                options[1] -> LocaleHelper.setLocale(requireActivity(), "ar")

            }
        }
        dialogBuilder.setItems(options,onOptionsClick).show()
    }

    private fun showThemOptionsDialog() {
        val dialogBuilder= MaterialAlertDialogBuilder(requireContext())
        var options :Array<out String>
        lateinit var onOptionsClick: (DialogInterface?, Int)-> Unit;

        options = resources.getStringArray(R.array.ThemOptions)
        onOptionsClick = { dialog, item ->
            when (options[item]) {
                options[0] -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        //delegate.applyDayNight()
                }
                options[1] -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                }
                options[2] -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

                }
            }
        }
        dialogBuilder.setItems(options,onOptionsClick).show()
    }

    private fun showChangeClassOptionsDialog() {

//        var gradesList = listOf<Grade>()
//        viewModel.gradesList.observe(viewLifecycleOwner){it->
//            it?.let {
//                gradesList = it
//            }
//        }
        viewModel.getGradestest()
        var gradesList = viewModel.gradesListstest

        val dialogBuilder= MaterialAlertDialogBuilder(requireContext())
       // var options :Array<out String>
        var options = gradesList.map {
            it.name_ar
        }
        lateinit var onOptionsClick: (DialogInterface?, Int)-> Unit;
        onOptionsClick = { dialog, item ->
            viewModel.setGrade(gradesList[item].id)
//            dialog?.dismiss()
        }
        dialogBuilder.setItems(options.toTypedArray(),onOptionsClick).show()
    }

}





