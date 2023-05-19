package com.vo.vo_kiosk.DTO

import com.google.gson.annotations.SerializedName

data class OrderData(

    var order : String,

)

data class OrderList(
    val menuId : String,
    val totalPrice: Int,
    val menuName : String,
    val menuImg : String,
    val drinkRadioId : Int?,
    val dessertRadioId : Int?,
    val drinkName : String?,
    val dessertName : String?
)

data class OrderDTO(
    val user_id: Int,
    val total_price: Int,
    val menu: List<MenuList>
)

data class MenuList(
    val menu_id: Int,
    val option_id1: Int? = null,
    val option_id2: Int? = null
)

data class OrderDTOResult(
    @SerializedName("result")
    val result : String
)

