package com.example.asimyamin.blood_donation.shared_pref

import com.example.asimyamin.blood_donation.activities.auth.model.DonorInformation

class SharedPrefHelper {
    //OnBoard Slider
    fun saveOnBoardSlider(isTrue: Boolean) {
        SharedPref.instance?.saveSliderState(ON_BOARDING_SLIDER, isTrue)
    }

    fun getOnBoardSliderState(): Boolean {
        return SharedPref.instance!!.getSliderState(ON_BOARDING_SLIDER)
    }

    fun saveIntegerValue(value: Int) {
        SharedPref.instance?.saveValue(ABC_VALUE, value)
    }

    val integerValue: Int
        get() = SharedPref.instance!!.getIntValue(ABC_VALUE)

    fun saveLoginState(isTrue: Boolean) {
        SharedPref.instance!!.saveValue(IS_LOGIN, isTrue)
    }

    val isLoggedIn: Boolean
        get() = SharedPref.instance!!.getBoolValue(IS_LOGIN)

    //login preferences
    fun saveLoginUser(user: DonorInformation?) {
        SharedPref.instance?.saveLoginData(LOGIN_DATA, user)
    }

    val loginUserData: DonorInformation
        get() = SharedPref.instance!!.getLoginData(LOGIN_DATA)

    companion object {
        //add key here
        private const val LOGIN_TYPE = "LOGIN_TYPE"
        private const val ABC_VALUE = "ABC_VALUE"
        private const val IS_LOGIN = "IS_LOGIN"
        private const val LOGIN_DATA = "LOGIN_DATA"
        private const val TAG_ID = "TAG_ID"
        private const val ON_BOARDING_SLIDER = "ON_BOARDING_SLIDER"
        var instance: SharedPrefHelper? = null
            get() {
                if (field == null) {
                    synchronized(SharedPrefHelper::class.java) {
                        if (field == null) {
                            field = SharedPrefHelper()
                        }
                    }
                }
                return field
            }
            private set
    }
}