package com.vo.vo_kiosk.NetWork

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vo.vo_kiosk.DTO.TokenSave

@Dao
interface UserDao {

    @Query("SELECT * FROM tokenId LIMIT 1")
    fun getUser(): TokenSave

    @Insert
    fun insert(token: TokenSave)
}