package com.vo.vo_kiosk.View.TabFragment

import android.annotation.SuppressLint
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
import com.vo.vo_kiosk.databinding.FragmentTotalMenuBinding

class TotalMenuFragment : Fragment(), MenuAdapter.OnItemClickListener{

    private var _binding : FragmentTotalMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : OrderDetailViewModel
    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTotalMenuBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[OrderDetailViewModel::class.java]

        val totalRecyclerView = binding.totalRecyclerView
        totalRecyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        val menuAdapter = MenuAdapter(this)
        totalRecyclerView.adapter = menuAdapter

        viewModel.getMenu("set")
        viewModel.allMenu.observe(viewLifecycleOwner, Observer { menuResponse ->
            menuAdapter.submitList(menuResponse.result)
        })

        return binding.root
    }

    override fun onItemClick(menu: MenuDTO, itemView: View) {
        val bundle = Bundle()

        bundle.putString("menuId", menu.mainId.toString())
        bundle.putString("menuName", menu.mainMenu)
        bundle.putString("menuPrice", menu.mainPrice.toString())
        bundle.putString("menuImg", menu.mainImg)
        findNavController().navigate(R.id.action_clickMenuFragment_to_orderDetailFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}