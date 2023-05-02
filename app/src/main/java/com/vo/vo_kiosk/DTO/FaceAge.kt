package com.vo.vo_kiosk.DTO

import com.google.gson.annotations.SerializedName

data class FaceAge(
    @SerializedName("result")
    val result : ageRange
)

data class ageRange(
    @SerializedName("result")
    val ageRange : String
)
