package com.vo.vo_kiosk.NetWork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit2 {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://oceanit.synology.me:3502/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ServerInterface::class.java)

    fun getInstance(): ServerInterface?{
        return api
    }

}