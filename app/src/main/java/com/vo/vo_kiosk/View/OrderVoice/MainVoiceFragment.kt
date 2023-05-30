package com.vo.vo_kiosk.View.OrderVoice

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.ViewModel.ShareQRViewModel
import com.vo.vo_kiosk.databinding.FragmentMainVoiceBinding

class MainVoiceFragment : Fragment() {

    private var _binding : FragmentMainVoiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: ShareQRViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainVoiceBinding.inflate(inflater, container, false)

//        qr 데이터 생성 후 Fragment 간 공유를 위한 ViewModel
        sharedViewModel = ViewModelProvider(requireActivity())[ShareQRViewModel::class.java]

        binding.button.setOnClickListener {
//            onButtonClicked()
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}