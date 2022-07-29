package id.rajadingin.consumer.ui.activity.interfaces

import android.text.TextWatcher

interface LoginActivityContractInterface {

    interface View {
        fun initView()
        fun navigateToMainActivity()
        fun navigateToRegisterActivity()
        fun showLoginToast(message: String)
        fun hideKeyboard()
        fun emptyCheck()
        fun saveNim()
        val nimTextwatcher: TextWatcher
//        fun saveUserDetail(userDetailList: UserDetail?)
        fun getUserDetailListFromSharedPreference()
    }

    interface Presenter {
        fun loginTap(user: String, pwd: String)
    }

}