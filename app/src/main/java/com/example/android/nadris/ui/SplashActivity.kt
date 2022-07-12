package com.example.android.nadris.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ActivitySplashBinding
import com.example.android.nadris.services.LocaleHelper
import com.example.android.nadris.ui.loginActivity.MainActivity
import com.example.android.nadris.ui.studentActivity.StudentMainActivity
import com.example.android.nadris.ui.teacherActivity.TeacherMainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    val TAG = "SplashActivity"
    lateinit var binding: ActivitySplashBinding
    lateinit var langSelcArray :Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val savedLangSel = LocaleHelper.getSavedLang(this)
        if (savedLangSel == null) {
            enableLangSelMode()
            binding.confirBtn.setOnClickListener {
                val langSelText = binding.spnLangSel.editText?.text.toString()
                if (langSelText.isEmpty())
                        Toast.makeText(this,
                            getString(R.string.lang_not_sel_msg),
                            Toast.LENGTH_SHORT)
                            .show()
                else {
                    val selectedItemIndex = langSelcArray.indexOf(langSelText)
                    val selectedLangCode = if (selectedItemIndex == 0) "en" else "ar"
                    LocaleHelper.setLocale(this, selectedLangCode)
                    disableLangSelMode()
                }
            }
        } else {
            LocaleHelper.refreshLangSelection(this)
            checkForUserAuth()
        }
    }

    private fun enableLangSelMode() {
        binding.nadrisSplashLogo.visibility = View.GONE
        binding.selectLanguageTitle.visibility = View.VISIBLE
        binding.spnLangSel.visibility = View.VISIBLE
        binding.confirBtn.visibility = View.VISIBLE
        langSelcArray =  resources.getStringArray(R.array.languages)
        val adapter = ArrayAdapter(this,
            R.layout.item_gender_list,langSelcArray)
        (binding.spnLangSel.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!

    }

    private fun disableLangSelMode() {
        binding.nadrisSplashLogo.visibility = View.VISIBLE
        binding.selectLanguageTitle.visibility = View.GONE
        binding.spnLangSel.visibility = View.GONE
        binding.confirBtn.visibility = View.GONE
        val adapter = ArrayAdapter(this,
            R.layout.item_gender_list,
            resources.getStringArray(R.array.languages))
        (binding.spnLangSel.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!

    }

    private fun checkForUserAuth() {
        val firebaseUser = NadrisApplication.instance?.repo?.getCurrentFirebaseUser()

        if (firebaseUser == null)
            navigateToLoginActivity()
        else {
            CoroutineScope(Dispatchers.Main).launch {
                getCurrentUserLocalData()
                navigateToHomeActivity()
            }
        }
    }

    private suspend fun getCurrentUserLocalData() {
        NadrisApplication.currentUserLocalData =
            NadrisApplication.instance?.repo?.getLocalUserData()
    }

    private fun navigateToHomeActivity() {

        when (NadrisApplication.currentUserLocalData?.Type) {
            true -> navigateToTeacherMainActivity()
            false -> navigateToStudentHomeActivity()
            else -> navigateToLoginActivity()
        }
    }

    private fun navigateToLoginActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun navigateToTeacherMainActivity() {
        startActivity(Intent(this, TeacherMainActivity::class.java))
    }

    private fun navigateToStudentHomeActivity() {
        startActivity(Intent(this, StudentMainActivity::class.java))
    }


}