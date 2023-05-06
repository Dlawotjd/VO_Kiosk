package com.vo.vo_kiosk.View.TabFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.vo.vo_kiosk.Adapter.MenuAdapter
import com.vo.vo_kiosk.DTO.MenuDTO
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.TotalMenuViewModel
import com.vo.vo_kiosk.databinding.FragmentTotalMenuBinding

class TotalMenuFragment : Fragment() {

    private var _binding : FragmentTotalMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TotalMenuViewModel

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTotalMenuBinding.inflate(inflater, container, false)

        val menuList = listOf(
            MenuDTO("menu1", R.drawable.hamburger, 10000),
            MenuDTO("menu2", R.drawable.hamburger, 8000),
            MenuDTO("menu3", R.drawable.hamburger, 12000),
            MenuDTO("menu4", R.drawable.hamburger, 9000),
            MenuDTO("menu5", R.drawable.hamburger, 15000),
            MenuDTO("menu1", R.drawable.hamburger, 10000),
            MenuDTO("menu2", R.drawable.hamburger, 8000),
            MenuDTO("menu3", R.drawable.hamburger, 12000),
            MenuDTO("menu4", R.drawable.hamburger, 9000),
            MenuDTO("menu5", R.drawable.hamburger, 15000),
            MenuDTO("menu1", R.drawable.hamburger, 10000),
            MenuDTO("menu2", R.drawable.hamburger, 8000),
            MenuDTO("menu3", R.drawable.hamburger, 12000),
            MenuDTO("menu4", R.drawable.hamburger, 9000),
            MenuDTO("menu5", R.drawable.hamburger, 15000),
        )

        val totalRecyclerView = binding.totalRecyclerView
        totalRecyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        val menuAdapter = MenuAdapter()
        totalRecyclerView.adapter = menuAdapter

        menuAdapter.submitList(menuList)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}