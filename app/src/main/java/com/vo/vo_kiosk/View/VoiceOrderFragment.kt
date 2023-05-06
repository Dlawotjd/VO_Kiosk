package com.vo.vo_kiosk.View

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.vo.vo_kiosk.ViewModel.MenuOrderViewModel
import com.vo.vo_kiosk.databinding.FragmentMenuOrderBinding

class VoiceOrderFragment : Fragment() {

    private var _binding : FragmentMenuOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MenuOrderViewModel

    private lateinit var speechRecognizer : SpeechRecognizer
    private lateinit var itd_t : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenuOrderBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[MenuOrderViewModel::class.java]

        val order_mic = binding.orderMic
        val sound_wave = binding.orderSoundWave
         itd_t = binding.itdT

        viewModel.orderData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.orderText.text = it.order
            }
        })


        binding.orderMic.setOnClickListener{
            order_mic.visibility = View.GONE
            sound_wave.visibility = View.VISIBLE
            startVoiceRecognition()
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
//          말하기 시작할 준비가 됐을때 호출
            override fun onReadyForSpeech(params: Bundle?) {}
//          말하기 시작했을 때 호출
            override fun onBeginningOfSpeech() {}
//          입력받는 소리의 크기를 알려줌
            override fun onRmsChanged(rmsdB: Float) {}
//          말을 시작하고 인식된 단어를 buffer에 담는다
            override fun onBufferReceived(buffer: ByteArray?) {}
//          말하기가 끝났을 떄
            override fun onEndOfSpeech() {}
//          에러 발생
            override fun onError(error: Int) {
                order_mic.visibility = View.VISIBLE
                sound_wave.visibility = View.GONE
                var message = ""
                when (error) {
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> {
                        message = "권한오류"
                    }
                    SpeechRecognizer.ERROR_AUDIO -> {
                        TODO()
                    }
                    SpeechRecognizer.ERROR_CANNOT_CHECK_SUPPORT -> {
                        TODO()
                    }
                    SpeechRecognizer.ERROR_CLIENT -> {
                        message = "클라이언트 오류"
                    }
                    SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED -> {

                    }
                    SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE -> {

                    }
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> {

                    }
                    SpeechRecognizer.ERROR_NO_MATCH -> {

                    }
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> {

                    }
                    SpeechRecognizer.ERROR_SERVER -> {

                    }
                    SpeechRecognizer.ERROR_SERVER_DISCONNECTED -> {

                    }
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> {

                    }
                    SpeechRecognizer.ERROR_TOO_MANY_REQUESTS -> {

                    }
                    SpeechRecognizer.ERROR_NETWORK -> {
                        message = "네트워크 오류"
                    }
                    else -> {
                        message = "알 수 없는 기타 에러"
                    }
                }
                Toast.makeText(context, "에러가 발생했습니다. $message", Toast.LENGTH_SHORT).show()
            }

//          인식 결과가 준비되면 호출
//          말을 하면 speechResult에 데이터를 넣는다
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (matches != null && matches.isNotEmpty()) {
                    val resultText = matches[0]
                    order_mic.visibility = View.VISIBLE
                    sound_wave.visibility = View.GONE

                    // 서버로 전송할 음성 인식 결과
                    viewModel.sendVoiceResult(resultText)
                }
            }
//          부분 인식결과를 사용할 수 있을 때 사용
            override fun onPartialResults(partialResults: Bundle?) {}
//          이벤트 추가 예약
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setDeniedMessage("만약 권한을 거부하시면 정상적인 기능 이용이 어려울 수 있습니다.\n설정 창에서 권한을 설정해주세요.")
            .setPermissions(android.Manifest.permission.RECORD_AUDIO)
            .check()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startVoiceRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now")
        }
        itd_t.text = "음성인식 중입니다."
        speechRecognizer.startListening(intent)
    }

//  마이크 음성 권환 획득
    private val permissionListener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            // 권한이 허용되었을 때 처리할 코드
            Log.d("TedPermission_result", "success")
        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {
            // 권한이 거부되었을 때 처리할 코드
            Log.d("TedPermission_result", "fail")
        }
    }
}