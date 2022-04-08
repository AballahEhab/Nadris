package com.example.android.nadris.ui.studentActivity.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSettingsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        binding.tvEditProfile.setOnClickListener {

        }

        binding.tvChangePassword.setOnClickListener {
            showAlertDailog()
        }

        binding.tvChangeClass.setOnClickListener {

        }

        binding.tvNightMode.setOnClickListener {

        }

        binding.tvChangeLanguage.setOnClickListener {

        }

        binding.tvHelp.setOnClickListener {
            this.findNavController()
                .navigate(SettingsFragmentDirections.actionSettingsFragmentToHelpFragment())

        }

        binding.tvSignOut.setOnClickListener {

        }



        return binding.root
    }
    fun showAlertDailog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Alert")
            .setMessage("message ")
            .setNeutralButton("letar"){dialog,which ->

            }
            .setNegativeButton("close"){dialog,which ->

            }
            .setPositiveButton("save"){dialog ,which ->

            }.show()
    }
//    private fun buildDialog() {
//        val builder = AlertDialog.Builder(this.requireContext(), R.style.WelcomeStyle)
//        val inflater = layoutInflater
//        builder.setTitle("Enter Tag")
//        val dialogLayout = inflater.inflate(R.layout.dialog, null)
//        val editText = dialogLayout.findViewById<EditText>(R.id.dialogEditText)
//        builder.setView(dialogLayout)
//
//        builder.setPositiveButton("OK") { _, _ ->
//            if (editText.text.toString().isNotEmpty()) {
//                viewModel.addTag(Tag(0, editText.text.toString()))
//            } else {
//                Toast.makeText(context, "Can not save empty Tag"
//                        + editText.text.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }
//        builder.show()
//    }




}