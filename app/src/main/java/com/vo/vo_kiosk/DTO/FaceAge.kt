package com.vo.vo_kiosk.DTO

import com.google.gson.annotations.SerializedName

data class FaceAge(
    @SerializedName("preictions")
    val preictions : String
)

data class FolderPath(
    @SerializedName("folder_path")
    val folder_path : String
)
