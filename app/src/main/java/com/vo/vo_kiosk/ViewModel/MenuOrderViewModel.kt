package com.vo.vo_kiosk.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vo.vo_kiosk.DB.MenuOrderRepository
import com.vo.vo_kiosk.DTO.OrderData

class MenuOrderViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = MenuOrderRepository()

    init {
        repository = MenuOrderRepository()
        repository.initOrder()
    }

    val orderData : LiveData<OrderData?> = repository.orderData

    fun sendVoiceResult(voiceResult: String) {
        repository.sendVoiceResult(voiceResult)
    }
}