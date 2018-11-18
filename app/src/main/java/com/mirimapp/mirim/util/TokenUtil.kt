package com.mirimapp.mirim.util

import android.content.*

/**
 * Created by root1 on 2017. 12. 15..
 */
object TokenUtil {
    private fun getPref(context: Context): SharedPreferences {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref
    }

    fun saveToken(context: Context, token: String, isAccessToken: Boolean = true) {
        val editor = getPref(context).edit()
        editor.putString(getKey(isAccessToken), token)
        editor.apply()
    }

    fun removeToken(context: Context, isAccessToken: Boolean = true) {
        val editor = getPref(context).edit()
        editor.remove(getKey(isAccessToken))
        editor.apply()
    }

    fun getToken(context: Context, isAccessToken: Boolean = true): String {
        val token = getPref(context).getString(getKey(isAccessToken), "")

        if (token.isNullOrEmpty()) {
            return ""
        } else {
            return "JWT $token"
        }
    }

    private fun getKey(isAccess: Boolean): String = if (isAccess) "AccessToken" else "RefreshToken"
}
