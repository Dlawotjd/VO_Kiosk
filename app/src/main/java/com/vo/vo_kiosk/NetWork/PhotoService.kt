package com.vo.vo_kiosk.NetWork

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface PhotoService {
    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part image: MultipartBody.Part?,
        @Part("description") description: RequestBody?,
    ): Call<ResponseDTO>

}