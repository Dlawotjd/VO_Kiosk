package com.vo.vo_kiosk.Repository

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.vo.vo_kiosk.DB.DataBase
import com.vo.vo_kiosk.DTO.TokenDTO
import com.vo.vo_kiosk.DTO.TokenResponse
import com.vo.vo_kiosk.DTO.TokenSave
import com.vo.vo_kiosk.NetWork.Retrofit2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityRepository(application : Application) {

    private val call by lazy{Retrofit2.getInstance()}

    private val _tokenId = MutableLiveData<TokenResponse?>()
    val tokenId : MutableLiveData<TokenResponse?> = _tokenId
    val dB = DataBase.getDBInstance(application)

    fun tokenSend(token : String) {

        call!!.tokenSend(TokenDTO(token)).enqueue(object : Callback<TokenResponse>{
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {

                if (response.isSuccessful) {
                    _tokenId.postValue(response.body())
                    tokenSaveDB(response.body()!!.user_id)
                } else {
                    Log.d("tokenId", "tokenId load Fail")
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Log.d("tokenId_Fail", "${t.message}")
            }

        })
    }

//  디바이스 토큰 정보 추출
    fun token(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->

            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, task.exception)
                return@OnCompleteListener

            } else {
                Log.d("token_error", "token_error")
            }

            val token = task.result
            if (token != null) {
//              토큰 전송 함수
                tokenSend(token)
                Log.d("token_send", "token_send")
            } else {
                Log.d("token Null", "Token Null")
            }
        })
    }

//  로컬 디비에 데이터 저장
    fun tokenSaveDB(userId : Int) {
        dB!!.UserDao().insert(TokenSave(userId))
    }
}