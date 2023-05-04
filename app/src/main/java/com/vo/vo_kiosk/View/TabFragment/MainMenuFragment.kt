package com.vo.vo_kiosk.View.TabFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.MainMenuViewModel
import com.vo.vo_kiosk.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {

    private var _binding : FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

}