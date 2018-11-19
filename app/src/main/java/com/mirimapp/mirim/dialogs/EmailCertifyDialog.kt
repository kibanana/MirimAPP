package com.mirimapp.mirim.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.dahyeon.mirim.R
import kotlinx.android.synthetic.main.dialog_email_certify.*

class EmailCertifyDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_email_certify)

        button_certify_confirm.setOnClickListener {
            Log.d("@@@", "qweqweqwe")
        }
    }
}