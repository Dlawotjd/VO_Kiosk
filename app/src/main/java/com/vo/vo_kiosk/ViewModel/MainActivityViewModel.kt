package com.vo.vo_kiosk.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.vo.vo_kiosk.Repository.MainActivityRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainActivityRepository(application)

    init {
        repository.token()
    }

    fun getTokenId() {
        repository.tokenId
        Log.d("성공", "성공")
    }

}