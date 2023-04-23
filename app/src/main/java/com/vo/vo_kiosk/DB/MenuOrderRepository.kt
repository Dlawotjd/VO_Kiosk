package com.vo.vo_kiosk.DB

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.vo.vo_kiosk.DTO.OrderData
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URISyntaxException

class MenuOrderRepository {

    private lateinit var mSocket: Socket
    private val gson = Gson()

    private val _orderData = MutableLiveData<OrderData?>()
    val orderData : MutableLiveData<OrderData?> = _orderData

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun initOrder() {
        try {
            mSocket = IO.socket("http://oceanit.synology.me:3501")
            Log.d("initOrder", "initOrder")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

        mSocket.connect()

        Log.d("socket_connect", "$mSocket")

        mSocket.on(Socket.EVENT_CONNECT, onConnected)
        Log.d("EVENT_CONNECT", "EVENT_CONNECT")

//      서버 데이터 확인
        mSocket.on("message", onMessageReceived)


    }

//  인식된 음성 서버에 데이터 전송
    fun sendVoiceResult(voiceResult : String){
        Log.d("voiceResult", voiceResult)
        ioScope.launch {
            val data = JSONObject().apply {
                put("android_message", voiceResult)
            }
            mSocket.emit("message", data)
        }
    }

    private val onConnected = Emitter.Listener {
        Log.d("SOCKET.IO", "Connected to the server")
    }

    private val onMessageReceived = Emitter.Listener { args ->
        val message = args[0].toString()
        Log.d("socket_message", message)
        ioScope.launch {
            withContext(Dispatchers.Main) {
                _orderData.value = OrderData(order = message)
            }
        }
    }

}