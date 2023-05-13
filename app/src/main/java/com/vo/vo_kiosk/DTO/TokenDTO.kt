package com.vo.vo_kiosk.DTO

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TokenDTO(
    @SerializedName("token")
    var token : String
)

data class TokenResponse(
    @SerializedName("user_id")
    val user_id : Int
)

@Entity(tableName = "tokenId")
data class TokenSave(
    @PrimaryKey val userInt: Int
)
