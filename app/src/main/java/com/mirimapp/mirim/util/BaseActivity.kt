package com.mirimapp.mirim.util

import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun showToast(message: String) {
        ViewUtil.showToast(this, message)
    }

    fun removeToken(isAccess: Boolean = true) = TokenUtil.removeToken(this, isAccess)

    fun saveToken(token: String, isAccessToken: Boolean = true) = TokenUtil.saveToken(this, token, isAccessToken)

    fun getToken(isAccess: Boolean = true): String = TokenUtil.getToken(this, isAccess)
}
