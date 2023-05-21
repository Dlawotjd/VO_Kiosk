package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vo.vo_kiosk.Adapter.ShoppingAdapter
import com.vo.vo_kiosk.DB.DataBase
import com.vo.vo_kiosk.DTO.MenuList
import com.vo.vo_kiosk.DTO.OrderDTO
import com.vo.vo_kiosk.NetWork.UserDao
import com.vo.vo_kiosk.ViewModel.OrderMenuViewModel
import com.vo.vo_kiosk.ViewModel.ShareQRViewModel
import com.vo.vo_kiosk.databinding.FragmentShopingBinding

class ShoppingFragment : Fragment() {

    private var _binding : FragmentShopingBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderMenuViewModel: OrderMenuViewModel
    private lateinit var shareQRViewModel: ShareQRViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : ShoppingAdapter
    private lateinit var userDao: UserDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentShopingBinding.inflate(inflater, container, false)

        orderMenuViewModel = ViewModelProvider(requireActivity())[OrderMenuViewModel::class.java]
        shareQRViewModel = ViewModelProvider(requireActivity())[ShareQRViewModel::class.java]

        userDao = DataBase.getDBInstance(requireContext())!!.UserDao()

        val tokenId = userDao.getUser()

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

            val orderList = orderMenuViewModel.orders.value
            if (orderList.isNullOrEmpty()) {
                return@setOnClickListener
            }
            val menuList = orderList.map { order ->
                MenuList(
                    menu_id = order.menuId.toInt(),
                    option_id1 = order.dessertRadioId,
                    option_id2 = order.drinkRadioId
                )
            }
            val orderDTO = OrderDTO(
                user_id = tokenId.userInt,
                total_price = binding.shopPrice.text.toString().replace("원", "").toInt(),
                menu = menuList
            )
//          장바구니 내에 있는 데이터 서버로 전송
            orderMenuViewModel.sendOrder(orderDTO)

            findNavController().popBackStack()

        }


        return binding.root
    }

}