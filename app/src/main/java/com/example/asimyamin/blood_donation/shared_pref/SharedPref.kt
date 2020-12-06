package com.example.asimyamin.blood_donation.shared_pref

import android.content.Context
import android.content.SharedPreferences
import com.example.asimyamin.blood_donation.MyApp
import com.example.asimyamin.blood_donation.activities.auth.model.DonorInformation
import com.google.gson.Gson
import kotlin.jvm.Synchronized

class SharedPref  //Private Constructor
private constructor() {
    private val sharedPreferences: SharedPreferences = MyApp.context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val sharedPreferencesSlider: SharedPreferences = MyApp.context.getSharedPreferences(SHARED_PREF_SLIDER, Context.MODE_PRIVATE)
    private val editorSlider = sharedPreferencesSlider.edit()
    fun saveSliderState(key: String?, value: Boolean) {
        editorSlider.putBoolean(key, value)
        editorSlider.apply()
    }

    fun getSliderState(key: String?): Boolean {
        return sharedPreferencesSlider.getBoolean(key, false)
    }

    fun saveValue(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun saveValue(key: String?, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun saveValue(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getStringValue(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }

    fun getIntValue(key: String?): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun clearSharedPref() {
        editor.clear().apply()
    }

    fun getBoolValue(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun saveLoginData(key: String?, user: DonorInformation?) {
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(key, json)
        editor.apply()
    }

    //this method will give the logged in user
    fun getLoginData(key: String?): DonorInformation {
        val gson = Gson()
        val json = sharedPreferences.getString(key, null)
        return gson.fromJson(json, DonorInformation::class.java)
    }

    companion object {
        private const val SHARED_PREF = "IdiomsApp"
        private var mInstance: SharedPref? = null
        private const val SHARED_PREF_SLIDER = "SliderPref"

        @get:Synchronized
        val instance: SharedPref?
            get() {
                if (mInstance == null) {
                    mInstance = SharedPref()
                }
                return mInstance
            }
    }
}