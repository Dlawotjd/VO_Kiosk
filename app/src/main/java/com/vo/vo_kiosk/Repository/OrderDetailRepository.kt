package com.vo.vo_kiosk.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vo.vo_kiosk.DTO.MenuDTO
import com.vo.vo_kiosk.DTO.MenuResponse
import com.vo.vo_kiosk.NetWork.Retrofit2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDetailRepository {

    private val _allMenu = MutableLiveData<MenuResponse>()
    val allMenu: LiveData<MenuResponse>
        get() = _allMenu

    val call by lazy { Retrofit2.getInstance() }

    fun getMenu(tabMenu: String) {
        val call = call!!.cateGory(category = tabMenu)
        call.enqueue(object : Callback<MenuResponse> {
            override fun onResponse(call: Call<MenuResponse>, response: Response<MenuResponse>) {
                if (response.isSuccessful) {
                    _allMenu.postValue(response.body())
                } else {
                    Log.d("getMenu", "getMenuFail")
                }
            }

            override fun onFailure(call: Call<MenuResponse>, t: Throwable) {
                Log.d("getMenu", "API Fail ${t.message}")
            }
        })
    }

}

