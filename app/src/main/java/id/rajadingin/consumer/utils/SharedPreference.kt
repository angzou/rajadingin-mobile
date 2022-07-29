package id.rajadingin.consumer.utils

import android.content.Context
import android.content.SharedPreferences
import id.rajadingin.consumer.model.Const

class SharedPreference(context: Context) {
    val PREFS_NAME = Const.PREF_NAME
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun save(KEY_NAME: String, status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_NAME, status)
        editor.apply()
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun getValueInt(KEY_NAME: String): Int {
        return sharedPref.getInt(KEY_NAME, 0)
    }

    fun getValueBoolean(KEY_NAME: String): Boolean {
        return sharedPref.getBoolean(KEY_NAME, false)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor.clear()
        editor.apply()
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

//    fun setUserDetailLists(userDetailList: UserDetail?): Boolean {
//
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//
//        val gson = Gson()
//        val json = gson.toJson(userDetailList)
//        editor.putString(Const.USER_DETAIL,json)
//        return editor.commit()
//
//    }
//
//    fun getUserDetailLists(): UserDetail?{
//
//        val gson = Gson()
//        val json = sharedPref.getString(Const.USER_DETAIL,null)
//        val type = object : TypeToken<UserDetail>(){}.type
//        return gson.fromJson(json, type)
//
//    }

}
