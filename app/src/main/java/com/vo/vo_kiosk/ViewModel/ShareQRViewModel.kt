package com.vo.vo_kiosk.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareQRViewModel : ViewModel() {
    private val _qrData = MutableLiveData<List<String>>()
    val qrData : MutableLiveData<List<String>> = _qrData

    fun setQRData(dataList : List<String>) {
        _qrData.value = dataList
    }
}