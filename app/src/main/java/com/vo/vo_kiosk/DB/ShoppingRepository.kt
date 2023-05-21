package com.vo.vo_kiosk.DB

import android.util.Log
import com.vo.vo_kiosk.DTO.OrderDTO
import com.vo.vo_kiosk.DTO.OrderDTOResult
import com.vo.vo_kiosk.NetWork.Retrofit2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingRepository {

    private val call by lazy { Retrofit2.getInstance() }

    fun shopApi(order: OrderDTO, callback: (OrderDTOResult?) -> Unit) {
        call!!.sendMenu(order).enqueue(object : Callback<OrderDTOResult>{
            override fun onResponse(call: Call<OrderDTOResult>, response: Response<OrderDTOResult>) {
                if (response.isSuccessful) {
                    callback(response.body())
                    Log.d("shopAPI_Success", response.body()!!.order_number_id.toString())
                }else {
                    Log.d("shopAPI_Fail", "실패")
                }
            }

            override fun onFailure(call: Call<OrderDTOResult>, t: Throwable) {
                Log.d("shpAPI_Fail", t.message.toString())
                callback(null)
            }

        })
    }
}