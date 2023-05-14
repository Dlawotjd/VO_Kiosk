package com.vo.vo_kiosk.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.vo.vo_kiosk.Adapter.TabLayoutAdapter
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.databinding.FragmentClickMenuBinding

class ClickMenuFragment : Fragment() {

    private var _binding : FragmentClickMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentClickMenuBinding.inflate(inflater, container, false)

        val tabLayout = binding.clickTabLayout
        val viewPager2 = binding.viewPager2

        val floatingButton = binding.floatingButton
        floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_clickMenuFragment_to_shoppingFragment)
        }

        viewPager2.apply {
            adapter = TabLayoutAdapter(this@ClickMenuFragment)
        }

        TabLayoutMediator(tabLayout, viewPager2){tab, position ->

            when(position) {
                0 -> {
                    tab.text = "햄버거세트"
                }
                1 -> {
                    tab.text = "햄버거"
                }
                2 -> {
                    tab.text = "음료"
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