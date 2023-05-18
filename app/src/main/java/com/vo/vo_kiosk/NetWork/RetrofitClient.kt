package com.vo.vo_kiosk.NetWork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://oceanit.synology.me:3505/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(PhotoService::class.java)

    // 다른 페이지에서 선언해 사용 초기화
    fun getInstance(): PhotoService?{
        return api
    }
}