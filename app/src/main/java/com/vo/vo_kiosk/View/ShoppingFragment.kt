package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vo.vo_kiosk.Adapter.ShoppingAdapter
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.OrderMenuViewModel
import com.vo.vo_kiosk.ViewModel.ShopingViewModel
import com.vo.vo_kiosk.databinding.FragmentShopingBinding

class ShoppingFragment : Fragment() {

    private var _binding : FragmentShopingBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderMenuViewModel: OrderMenuViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : ShoppingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentShopingBinding.inflate(inflater, container, false)

        orderMenuViewModel = ViewModelProvider(requireActivity())[OrderMenuViewModel::class.java]

        adapter = ShoppingAdapter()
        adapter.setViewModel(orderMenuViewModel)

        orderMenuViewModel.orders.observe(viewLifecycleOwner) { orders ->
            Log.d("Shopping Data", orders.toString())
            adapter.submitList(orders)

            var totalPrice = 0
            for (order in orders) {
                totalPrice += order.totalPrice
            }
            binding.shopPrice.text = "${totalPrice}원"
        }

        recyclerView = binding.shoppingRecyclerView
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.shoppingFinish.setOnClickListener {

        }

        return binding.root
    }

}