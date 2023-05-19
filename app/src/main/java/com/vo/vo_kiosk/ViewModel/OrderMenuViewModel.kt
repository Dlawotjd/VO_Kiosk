package com.vo.vo_kiosk.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vo.vo_kiosk.DTO.OrderList

class OrderMenuViewModel(application: Application) : AndroidViewModel(application) {

    private val _orders = MutableLiveData<List<OrderList>>()
    val orders : LiveData<List<OrderList>> = _orders

    fun addOrder(order: OrderList){
        val currentOrder = _orders.value ?: emptyList()
        _orders.value = currentOrder + order
        Log.d("viewModel add", order.toString())
    }

    fun removeOrder(order: OrderList){
        val currentOrder = _orders.value ?: emptyList()
        _orders.value = currentOrder - order
    }
}