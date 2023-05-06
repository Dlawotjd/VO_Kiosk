package com.vo.vo_kiosk.View.TabFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.vo.vo_kiosk.Adapter.MenuAdapter
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.SideMenuViewModel
import com.vo.vo_kiosk.databinding.FragmentSideMenuBinding

class SideMenuFragment : Fragment() {

    private var _binding : FragmentSideMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SideMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSideMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.sideRecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        val menuAdapter = MenuAdapter()
        recyclerView.adapter = menuAdapter

        menuAdapter.submitList(null)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}