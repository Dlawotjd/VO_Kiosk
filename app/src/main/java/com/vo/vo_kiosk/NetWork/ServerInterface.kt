package com.vo.vo_kiosk.NetWork

import com.vo.vo_kiosk.DTO.MenuResponse
import com.vo.vo_kiosk.DTO.TokenDTO
import com.vo.vo_kiosk.DTO.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServerInterface {

//  토큰 전송 API
    @POST("login")
    fun tokenSend(
    @Body tokenDTO: TokenDTO
    ) : Call<TokenResponse>

//  카테고리 API
    @GET("/category")
    fun cateGory(
        @Query("category") category : String
    ) : Call<MenuResponse>

}

