package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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



        return binding.root
    }

}