package com.vo.vo_kiosk.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vo.vo_kiosk.DTO.MenuResponse
import com.vo.vo_kiosk.Repository.OrderDetailRepository

class OrderDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = OrderDetailRepository()
    val allMenu: LiveData<MenuResponse>
        get() = repository.allMenu

    fun getMenu(tabMenu: String) {
        repository.getMenu(tabMenu)
    }
}