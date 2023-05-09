package com.vo.vo_kiosk.View.TabFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vo.vo_kiosk.Adapter.MenuAdapter
import com.vo.vo_kiosk.DTO.MenuDTO
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.DesertMenuViewModel
import com.vo.vo_kiosk.databinding.FragmentDesertBinding

class DesertMenuFragment : Fragment(), MenuAdapter.OnItemClickListener {

    private var _binding : FragmentDesertBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DesertMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDesertBinding.inflate(inflater, container, false)

        val desertRecyclerView = binding.desertRecyclerView
        desertRecyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        val menuAdapter = MenuAdapter(this)
        desertRecyclerView.adapter = menuAdapter

        menuAdapter.submitList(null)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(menu: MenuDTO, itemView : View) {
        findNavController().navigate(R.id.action_clickMenuFragment_to_orderDetailFragment)
    }

}