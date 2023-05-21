package com.vo.vo_kiosk.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import com.vo.vo_kiosk.Adapter.QRAdapter
import com.vo.vo_kiosk.Adapter.QRItemAdapter

import com.vo.vo_kiosk.Adapter.TabLayoutAdapter
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.OrderMenuViewModel
import com.vo.vo_kiosk.ViewModel.ShareQRViewModel
import com.vo.vo_kiosk.databinding.FragmentClickMenuBinding

class ClickMenuFragment : Fragment() {

    private var _binding : FragmentClickMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var shareQRViewModel : ShareQRViewModel
    private lateinit var orderMenuViewModel : OrderMenuViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentClickMenuBinding.inflate(inflater, container, false)

        shareQRViewModel = ViewModelProvider(requireActivity())[ShareQRViewModel::class.java]
        orderMenuViewModel = ViewModelProvider(requireActivity())[OrderMenuViewModel::class.java]

        val tabLayout = binding.clickTabLayout
        val viewPager2 = binding.viewPager2

        val floatingButton = binding.floatingButton
        floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_clickMenuFragment_to_shoppingFragment)
        }

        viewPager2.apply {
            adapter = TabLayoutAdapter(this@ClickMenuFragment)
        }

        TabLayoutMediator(tabLayout, viewPager2){tab, position ->

            when(position) {
                0 -> {
                    tab.text = "햄버거세트"
                }
                1 -> {
                    tab.text = "햄버거"
                }
                2 -> {
                    tab.text = "음료"
                }
                3 -> {
                    tab.text = "디저트"
                }
            }
        }.attach()

        shareQRViewModel.qrData.observe(viewLifecycleOwner) { qrDataList ->
            Log.d("shareQRLog", qrDataList.toString())
            // 각각의 ViewPager2에 대해 새로운 QRAdapter 인스턴스를 생성하여 연결합니다.
            val qrPagerAdapterForBottomSheet = QRAdapter(qrDataList)
            binding.totalBottomSheet.qrViewPager.adapter = qrPagerAdapterForBottomSheet
        }

        viewPager2.apply {
            // TabLayout과 연결된 ViewPager는 TabLayoutAdapter를 사용합니다.
            adapter = TabLayoutAdapter(this@ClickMenuFragment)
        }

        orderMenuViewModel.orderResult.observe(viewLifecycleOwner) { orderResult ->
            Log.d("orderMenuViewModel Main", orderResult!!.order_number_id.toString())
            shareQRViewModel.setNewQRData(orderResult.order_number_id)

        }
        orderMenuViewModel.orderList.observe(viewLifecycleOwner) {orderList ->
            Log.d("orderMenuViewModel orderList", orderList.toString())
            val adapter = QRItemAdapter(orderList)
            val recyclerView = binding.totalBottomSheet.qrTextRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

        }

        binding.totalBottomSheet.bottomConstrainLayout2.setOnClickListener {
            val clickedView = binding.totalBottomSheet.bottomConstrainLayout2
            val currentBehavior = BottomSheetBehavior.from(clickedView)

            if (currentBehavior.isDraggable) {
                currentBehavior.isDraggable = false
            } else {
                currentBehavior.isDraggable = true
            }
        }




        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}