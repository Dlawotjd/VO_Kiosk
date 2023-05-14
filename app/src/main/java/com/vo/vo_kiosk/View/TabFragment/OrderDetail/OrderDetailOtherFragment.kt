package com.vo.vo_kiosk.View.TabFragment.OrderDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.OrderDetailOtherViewModel
import com.vo.vo_kiosk.databinding.FragmentOrderDetailBinding

class OrderDetailOtherFragment : Fragment() {

    private var _binding : FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: OrderDetailOtherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}