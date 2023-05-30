package com.vo.vo_kiosk.View.OrderClick

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
import com.vo.vo_kiosk.databinding.FragmentSideMenuBinding

class SideMenuFragment : Fragment(), MenuAdapter.OnItemClickListener {

    private var _binding : FragmentSideMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : OrderDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSideMenuBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[OrderDetailViewModel::class.java]

        val recyclerView = binding.sideRecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        val menuAdapter = MenuAdapter(this)
        recyclerView.adapter = menuAdapter

        viewModel.getMenu("drink")
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
        val bundle = Bundle()

        bundle.putString("menuId", menu.mainId.toString())
        bundle.putString("menuName", menu.mainMenu)
        bundle.putString("menuPrice", menu.mainPrice.toString())
        bundle.putString("menuImg", menu.mainImg)
        findNavController().navigate(R.id.action_clickMenuFragment_to_orderDetailOtherFragment, bundle)
    }

}