package com.example.android.nadris.ui.studentActivity.settings

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSettingsBinding
import com.example.android.nadris.services.LocaleHelper
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
            this.findNavController()
                .navigate(SettingsFragmentDirections.actionSettingsFragmentToHelpFragment())
        }

        binding.tvChangeClass.setOnClickListener {
            showAlertDailog("ChangeClass","هل تريد تغيير الصف الدراسي")
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

        binding.tvSignOut.setOnClickListener {
            showAlertDailog("SignOut","هل تريد تسجيل الدخول")
        }



        return binding.root
    }
    fun showAlertDailog(Alert :String,message:String){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(Alert)
            .setMessage(message)
            .setNegativeButton("no"){dialog,which ->
                dialog.dismiss()
            }
            .setPositiveButton("yes"){dialog ,which ->
                // code log out

            }.show()
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
        lateinit var onOptionsClick: (DialogInterface?, Int)-> Unit

        options = resources.getStringArray(R.array.ThemOptions)
        onOptionsClick = { dialog, item ->
            when (options[item]) {
                options[0] -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        //delegate.applyDayNight()
                }
                options[1] -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    //delegate.applyDayNight()
                }
                options[2] -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    //delegate.applyDayNight()
                }
            }
        }
        dialogBuilder.setItems(options,onOptionsClick).show()
    }


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




