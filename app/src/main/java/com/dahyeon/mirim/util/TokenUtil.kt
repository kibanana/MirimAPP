package com.dahyeon.mirim.util

import android.content.*

/**
 * Created by root1 on 2017. 12. 15..
 */
object TokenUtil {
    private fun getPref(context: Context): SharedPreferences {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref
    }

    fun saveToken(context: Context, token: String, isAccess: Boolean = true) {
        val editor = getPref(context).edit()
        editor.putString(getKey(isAccess), token)
        editor.apply()
    }

    fun removeToken(context: Context, isAccess: Boolean = true) {
        val editor = getPref(context).edit()
        editor.remove(getKey(isAccess))
        editor.apply()
    }

    fun getToken(context: Context, isAccess: Boolean = true): String {
        val token = getPref(context).getString(getKey(isAccess), "")

        if (token.isNullOrEmpty()) {
            return ""
        } else {
            return "JWT $token"
        }
    }

    private fun getKey(isAccess: Boolean): String = if (isAccess) "AccessToken" else "RefreshToken"
}
