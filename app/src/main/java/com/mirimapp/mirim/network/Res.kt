package com.mirimapp.mirim.network

import android.content.*
import com.mirimapp.mirim.util.TokenUtil
import com.mirimapp.mirim.util.ViewUtil
import retrofit2.*

/**
 * Created by root1 on 2017. 11. 23..
 */
abstract class Res<T>(val context: Context): Callback<T> {

    abstract fun callBack(code: Int, body: T?)

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        ViewUtil.showToast(context, "네트워크 오류")
    }
    override fun onResponse(call: Call<T>?, response: Response<T>) {
        val code = response.code()
        val body = response.body()
        when(code) {
            500 -> ViewUtil.showToast(context, "서버 오류")
            422 -> {
                ViewUtil.showToast(context, "로그인이 필요합니다")
                TokenUtil.removeToken(context)
            }
            403 -> ViewUtil.showToast(context, "권한이 없습니다")
            else -> callBack(code, body)
        }
    }
}
