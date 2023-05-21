package com.vo.vo_kiosk.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vo.vo_kiosk.DB.ShoppingRepository
import com.vo.vo_kiosk.DTO.OrderDTO
import com.vo.vo_kiosk.DTO.OrderDTOResult
import com.vo.vo_kiosk.DTO.OrderList

class OrderMenuViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShoppingRepository()
    private val _orders = MutableLiveData<List<OrderList>>()
    val orders : LiveData<List<OrderList>> = _orders

    private val _orderResult = MutableLiveData<OrderDTOResult?>()
    val orderResult : LiveData<OrderDTOResult?> = _orderResult

    private val _orderList = MutableLiveData<List<OrderList>>()
    val orderList : LiveData<List<OrderList>> = _orderList


    fun addOrder(order: OrderList){
        val currentOrder = _orders.value ?: emptyList()
        _orders.value = currentOrder + order
        Log.d("viewModel add", order.toString())
    }

    fun removeOrder(order: OrderList){
        val currentOrder = _orders.value ?: emptyList()
        _orders.value = currentOrder - order
    }

    fun clearOrder() {
        _orders.value = emptyList()
    }

    fun sendOrder(orderDTO: OrderDTO){
        repository.shopApi(orderDTO) { result ->

            _orderResult.postValue(OrderDTOResult(result!!.order_number_id))
            _orderList.postValue(_orders.value)
            if (result != null) {
                clearOrder()
            }

        }
    }
}
