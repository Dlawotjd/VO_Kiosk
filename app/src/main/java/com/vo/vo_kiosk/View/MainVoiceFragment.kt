package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.vo.vo_kiosk.ViewModel.MainVoiceViewModel
import com.vo.vo_kiosk.ViewModel.ShareQRViewModel
import com.vo.vo_kiosk.databinding.FragmentMainBinding
import com.vo.vo_kiosk.databinding.FragmentMainVoiceBinding
import kotlinx.coroutines.launch

class MainVoiceFragment : Fragment() {

    private var _binding : FragmentMainVoiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainVoiceViewModel
    private lateinit var sharedViewModel: ShareQRViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainVoiceBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[MainVoiceViewModel::class.java]
//        qr 데이터 생성 후 Fragment 간 공유를 위한 ViewModel
        sharedViewModel = ViewModelProvider(requireActivity())[ShareQRViewModel::class.java]

        binding.button.setOnClickListener {
            onButtonClicked()
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun onButtonClicked() {
        val newDataList = generateNewDataList()
        sharedViewModel.setQRData(newDataList)
    }

    // QR 데이터 목록을 생성하는 코드 (샘플 코드)
    private fun generateNewDataList(): List<String> {
        // QR 데이터 목록을 생성하는 로직을 여기에 구현하세요.
        return listOf("data1", "data2", "data3")
    }
}