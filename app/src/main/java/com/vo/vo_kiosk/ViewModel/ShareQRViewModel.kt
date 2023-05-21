package com.vo.vo_kiosk.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareQRViewModel(application: Application) : AndroidViewModel(application) {

    private val _qrData = MutableLiveData<List<Int>>()
    val qrData : MutableLiveData<List<Int>> = _qrData

    // 새로운 메소드: 기존의 데이터를 모두 지우고 새로운 데이터만을 추가합니다.
    fun setNewQRData(qrData : Int) {
        _qrData.value = listOf(qrData)
    }
}