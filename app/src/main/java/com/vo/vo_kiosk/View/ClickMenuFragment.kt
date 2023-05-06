package com.vo.vo_kiosk.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.vo.vo_kiosk.Adapter.TabLayoutAdapter
import com.vo.vo_kiosk.ViewModel.ClickMenuViewModel
import com.vo.vo_kiosk.databinding.FragmentClickMenuBinding

class ClickMenuFragment : Fragment() {

    private var _binding : FragmentClickMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ClickMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentClickMenuBinding.inflate(inflater, container, false)

        val tabLayout = binding.clickTabLayout
        val viewPager2 = binding.viewPager2

        viewPager2.apply {
            adapter = TabLayoutAdapter(this@ClickMenuFragment)
        }

        TabLayoutMediator(tabLayout, viewPager2){tab, position ->

            when(position) {
                0 -> {
                    tab.text = "추천 메뉴"
                }
                1 -> {
                    tab.text = "햄버거"
                }
                2 -> {
                    tab.text = "사이드"
                }
                3 -> {
                    tab.text = "디저트"
                }
            }
        }.attach()

        binding.viewPager2

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}