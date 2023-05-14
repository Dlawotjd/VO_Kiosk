package com.vo.vo_kiosk.DTO

import com.google.gson.annotations.SerializedName

data class MenuResponse(
    @SerializedName("result")
    val result : ArrayList<MenuDTO>
)
data class MenuDTO(

    @SerializedName("menu_id")
    val mainId : Int,

    @SerializedName("name")
    val mainMenu : String,

    @SerializedName("price")
    val mainPrice : Int,

    @SerializedName("menu_url")
    val mainImg : String,
    
)
