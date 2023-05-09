package com.vo.vo_kiosk.View.TabFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.vo.vo_kiosk.Adapter.MenuAdapter
import com.vo.vo_kiosk.DTO.MenuDTO
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.TotalMenuViewModel
import com.vo.vo_kiosk.databinding.BottomSheetBinding
import com.vo.vo_kiosk.databinding.FragmentTotalMenuBinding

class TotalMenuFragment : Fragment(), MenuAdapter.OnItemClickListener{

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

        val menuAdapter = MenuAdapter(this)
        totalRecyclerView.adapter = menuAdapter
        menuAdapter.submitList(menuList)

        return binding.root
    }

    override fun onItemClick(menu: MenuDTO, itemView: View) {
        val bundle = Bundle()
        bundle.putString("menu", menu.mainMenu)
        findNavController().navigate(R.id.action_clickMenuFragment_to_orderDetailFragment, bundle)
    }

    override fun onPause() {
        super.onPause()
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.totalBottomSheet.bottomSheetLayout)
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}