package com.mirimapp.mirim.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.dahyeon.mirim.R
import com.mirimapp.mirim.network.Connector
import com.mirimapp.mirim.network.Res
import com.mirimapp.mirim.util.ViewUtil
import kotlinx.android.synthetic.main.dialog_email_certify.*

class EmailCertifyDialog(context: Context, emailWithSuffix: String): Dialog(context) {
    val emailWithSuffix = emailWithSuffix

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_email_certify)

        button_certify_confirm.setOnClickListener {
            val certifyCode = edittext_certify_code.text.toString()

            if(certifyCode.isEmpty()) {
                ViewUtil.showToast(context, "인증 코드를 입력해 주세요.")
            } else {
                Connector.api.certifyCode(
                    emailWithSuffix, hashMapOf(
                        "code" to certifyCode
                    )
                ).enqueue(object : Res<Void>(context) {
                    override fun callBack(code: Int, body: Void?) {
                        when (code) {
                            400 -> "인증 코드를 다시 전송해 주세요."
                            401 -> "인증 코드가 올바르지 않습니다."
                            200 -> null
                            else -> "오류: $code"
                        }.let {
                            if (it != null) {
                                ViewUtil.showToast(context, it)
                            } else {
                                ViewUtil.showToast(context, "인증에 성공했습니다.")
                                this@EmailCertifyDialog.dismiss()
                            }
                        }
                    }
                })
            }
        }
    }
}
