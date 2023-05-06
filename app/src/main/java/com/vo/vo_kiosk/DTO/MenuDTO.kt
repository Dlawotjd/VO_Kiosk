package com.vo.vo_kiosk.DTO

import com.google.gson.annotations.SerializedName

data class MenuDTO(
    @SerializedName("mainMenu")
    val mainMenu : String,

    @SerializedName("img")
    val mainImg : Int,

    @SerializedName("price")
    val mainPrice : Int
)
