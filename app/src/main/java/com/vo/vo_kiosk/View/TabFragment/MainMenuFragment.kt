package com.vo.vo_kiosk.View.TabFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.vo.vo_kiosk.Adapter.MenuAdapter
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

        val mainRecyclerView = binding.mainRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        val menuAdapter = MenuAdapter()
        mainRecyclerView.adapter = menuAdapter

        menuAdapter.submitList(null)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}