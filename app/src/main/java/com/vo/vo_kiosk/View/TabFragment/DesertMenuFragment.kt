package com.vo.vo_kiosk.View.TabFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vo.vo_kiosk.Adapter.MenuAdapter
import com.vo.vo_kiosk.DTO.MenuDTO
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.OrderDetailViewModel
import com.vo.vo_kiosk.databinding.FragmentDesertBinding

class DesertMenuFragment : Fragment(), MenuAdapter.OnItemClickListener {

    private var _binding : FragmentDesertBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : OrderDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDesertBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[OrderDetailViewModel::class.java]

        val desertRecyclerView = binding.desertRecyclerView
        desertRecyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        val menuAdapter = MenuAdapter(this)
        desertRecyclerView.adapter = menuAdapter

        viewModel.getMenu("dessert")
        viewModel.allMenu.observe(viewLifecycleOwner, Observer { menuResponse ->
            menuAdapter.submitList(menuResponse.result)
        })

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