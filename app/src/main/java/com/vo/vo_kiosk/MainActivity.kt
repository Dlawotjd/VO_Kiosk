package com.vo.vo_kiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.vo.vo_kiosk.DB.DataBase
import com.vo.vo_kiosk.NetWork.UserDao
import com.vo.vo_kiosk.ViewModel.MainActivityViewModel
import com.vo.vo_kiosk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : MainActivityViewModel
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = DataBase.getDBInstance(this)!!.UserDao()

        val tokenId = userDao.getUser()

        if (tokenId != null){
            Log.d("토큰값이 저장되어 있음", "토큰값이 저장되어 있음 ${tokenId}")
        } else {
            viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
            viewModel.getTokenId()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}