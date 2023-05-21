package com.vo.vo_kiosk.NetWork

import com.vo.vo_kiosk.DTO.ResponseDTO
import com.vo.vo_kiosk.DTO.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ServerInterface {

//  토큰 전송 API
    @POST("login")
    fun tokenSend(
    @Body tokenDTO: TokenDTO
    ) : Call<TokenResponse>

//  카테고리 API
    @GET("category")
    fun cateGory(
        @Query("category") category : String
    ) : Call<MenuResponse>

//  세트 메뉴 값 호출
    @GET("menuClick")
    fun menuClick(
        @Query("menu_id") menu_id : String
    ) : Call<MenuData>

//  장바구니 데이터 서버 전송
    @POST("cart")
    fun sendMenu(
        @Body order : OrderDTO
    ) : Call<OrderDTOResult>

//  나이 확인 모델 API
    @POST("classify")
    fun faceCheck(
        @Body folder_path: FolderPath
    ):Call<FaceAge>

//  이미지 업로드
    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part image: MultipartBody.Part?,
        @Part("description") description: RequestBody?,
    ): Call<ResponseDTO>
}

