package com.vo.vo_kiosk.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.VoiceMenuViewModel

class VoiceMenuFragment : Fragment() {

    private lateinit var viewModel: VoiceMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_voice_menu, container, false)
    }

}