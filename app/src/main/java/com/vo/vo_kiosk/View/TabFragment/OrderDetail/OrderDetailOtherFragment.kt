package com.vo.vo_kiosk.View.TabFragment.OrderDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vo.vo_kiosk.ViewModel.OrderDetailOtherViewModel
import com.vo.vo_kiosk.databinding.FragmentOrderDetailOtherBinding

class OrderDetailOtherFragment : Fragment() {

    private var _binding : FragmentOrderDetailOtherBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: OrderDetailOtherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderDetailOtherBinding.inflate(inflater, container, false)

        val menuId = arguments?.getString("menuId")
        val menuName = arguments?.getString("menuName")
        val menuImg = arguments?.getString("menuImg")
        val menuPrice = arguments?.getString("menuPrice")

        Log.d("dessert", menuName.toString())

        binding.odoTextView.text = menuName
        binding.odoPrice.text = "${menuPrice}원"

        Glide.with(this)
            .load("http://oceanit.synology.me:3502/img/${menuImg}")
            .into(binding.odoImageView)

//      장바구니에 들어가는 코드
        binding.odoButton.setOnClickListener {

        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}