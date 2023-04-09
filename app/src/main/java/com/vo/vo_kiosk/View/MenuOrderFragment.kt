package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.ViewModel.MenuOrderViewModel
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.databinding.FragmentMenuOrlderBinding

class MenuOrderFragment : Fragment() {

    private var _binding : FragmentMenuOrlderBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MenuOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMenuOrlderBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[MenuOrderViewModel::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}