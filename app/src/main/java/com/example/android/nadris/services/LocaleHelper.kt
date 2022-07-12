package com.example.android.nadris.services

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import com.example.android.nadris.R
import java.util.*

object LocaleHelper {

     fun setLocale(activity: Activity, language: String) {
        //save the choosen lanaguage
         saveUserSelectionToSharedPreference(activity ,language)
        val myLocale = Locale(language)
        val res: Resources = activity.baseContext.resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(activity.baseContext, activity.javaClass)
        activity.finish()
        activity.startActivity(refresh)

    }

    fun refreshLangSelection(activity: Activity){
        val langSel = getSavedLang(activity)
        setLocale(activity, langSel!!)
    }

    private fun saveUserSelectionToSharedPreference(activity: Activity, lang:String){
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(activity.getString(R.string.user_lang_pre),lang)
        editor.apply()
    }

     fun getSavedLang(activity: Activity): String? {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(activity.getString(R.string.user_lang_pre),null)
    }



}
