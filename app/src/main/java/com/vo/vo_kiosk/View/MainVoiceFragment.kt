package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.MainVoiceViewModel
import com.vo.vo_kiosk.databinding.FragmentMainVoiceBinding

class MainVoiceFragment : Fragment() {

    private var _binding : FragmentMainVoiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainVoiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainVoiceBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[MainVoiceViewModel::class.java]


        binding.mainVoice.setOnClickListener {
            findNavController().navigate(R.id.action_mainVoiceFragment_to_menuOrlderFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}