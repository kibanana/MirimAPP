package com.dahyeon.mirim.util

import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun showToast(message: String) {
        ViewUtil.showToast(this, message)
    }

    fun saveToken(token: String, isAccess: Boolean = true) = TokenUtil.saveToken(this, token, isAccess)

    fun getToken(isAccess: Boolean = true): String = TokenUtil.getToken(this, isAccess)
}
