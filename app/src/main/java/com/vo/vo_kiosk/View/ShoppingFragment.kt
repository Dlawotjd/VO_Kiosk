package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.ShopingViewModel
import com.vo.vo_kiosk.databinding.FragmentShopingBinding

class ShoppingFragment : Fragment() {

    private var _binding : FragmentShopingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ShopingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentShopingBinding.inflate(inflater, container, false)

        binding.shoppingRecyclerView

        return binding.root
    }

}