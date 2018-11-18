package com.dahyeon.mirim.util

import android.content.Context
import android.widget.Toast

object ViewUtil {
    fun showToast(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
