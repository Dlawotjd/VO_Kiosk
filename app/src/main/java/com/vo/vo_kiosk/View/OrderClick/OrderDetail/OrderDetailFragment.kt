package com.vo.vo_kiosk.View.OrderClick.OrderDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.vo.vo_kiosk.DTO.MenuData
import com.vo.vo_kiosk.DTO.MenuOption
import com.vo.vo_kiosk.DTO.OrderList
import com.vo.vo_kiosk.NetWork.Retrofit2
import com.vo.vo_kiosk.ViewModel.OrderMenuViewModel
import com.vo.vo_kiosk.databinding.FragmentOrderDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrderDetailFragment : Fragment() {

    private var _binding : FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!
    private var totalPrice : TextView? = null
    private var basePrice: Int = 0
    private var baseDrinkPrice: Int = 0
    private var baseDessertPrice: Int = 0
    private var isDrinkSelected = false
    private var isDessertSelected = false
    private var drinkRadioName: String? = null
    private var dessertRadioName: String? = null

    private lateinit var orderMenuViewModel : OrderMenuViewModel

    val call by lazy { Retrofit2.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)

        orderMenuViewModel = ViewModelProvider(requireActivity())[OrderMenuViewModel::class.java]

        val menuId = arguments?.getString("menuId")
        val menuName = arguments?.getString("menuName")
        val menuImg = arguments?.getString("menuImg")
        val menuPrice = arguments?.getString("menuPrice")

        binding.detailTextView.text = menuName
        binding.detailPrice.text = "${menuPrice}원"

        totalPrice = binding.detailTotalPrice
        basePrice = menuPrice!!.toInt()
        totalPrice!!.text = "${basePrice}원" // 초기 총 가격은 기본 가격

        if (menuId != null) {
            menuAPI(menuId)
        }

        Glide.with(this)
            .load("http://oceanit.synology.me:3502/img/${menuImg}")
            .into(binding.detailImageView)

//      음료 라디오 버튼
        binding.drinkRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val price = group.findViewById<RadioButton>(checkedId)
            baseDrinkPrice = price?.tag as? Int ?: 0
            drinkRadioName = price?.text?.toString()
            updateTotalPrice()
        }
//      디저트 라디오 버튼
        binding.dessertRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val price = group.findViewById<RadioButton>(checkedId)
            baseDessertPrice = price?.tag as? Int ?: 0
            dessertRadioName = price?.text?.toString()
            updateTotalPrice()
        }
//      주문 완료시 장바구니 데이터 전송
        binding.detailFinButton.setOnClickListener {
            val totalPrice = binding.detailTotalPrice.text.toString().replace("원","").toInt()
            val drinkRadioId = binding.drinkRadioGroup.checkedRadioButtonId
            val dessertRadioId = binding.dessertRadioGroup.checkedRadioButtonId

            if (!isDrinkSelected || !isDessertSelected) {
                Toast.makeText(requireContext(), "음료와 디저트를 선택해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newOrder = OrderList(menuId.toString(), totalPrice, menuName.toString(), menuImg.toString(), drinkRadioId, dessertRadioId, drinkRadioName.toString(), dessertRadioName.toString())
            orderMenuViewModel.addOrder(newOrder)

            findNavController().popBackStack()

        }

        return binding.root
    }

//  총합 가격 데이터
    private fun updateTotalPrice() {
        val total = basePrice + baseDrinkPrice + baseDessertPrice
        totalPrice?.text = "${total}원"
        checkSelection()
    }
//  라디오 버튼 클릭 체크 확인
    private fun checkSelection() {
        isDrinkSelected = binding.drinkRadioGroup.checkedRadioButtonId != -1
        isDessertSelected = binding.dessertRadioGroup.checkedRadioButtonId != -1
    }

//  음료 메뉴 디저트 메뉴 버튼 동적 생성
    fun addRadioButton(number : Int, radioGroup: RadioGroup, optionList : List<MenuOption>) {
        for (i in 1..number) {
            val price = optionList[i-1].price
            val rdbtn = RadioButton(requireContext())
            rdbtn.id = optionList[i-1].menu_option_id
            rdbtn.text = "${optionList[i-1].name} ${optionList[i-1].price}원"
            rdbtn.tag = price  // set the price as the tag of the radio button
            radioGroup.addView(rdbtn)
        }
    }

//  메뉴 정보 호출
    fun menuAPI(menuId : String){

        call!!.menuClick(menuId).enqueue(object : Callback<MenuData>{

            override fun onResponse(call: Call<MenuData>, response: Response<MenuData>) {
                if (response.isSuccessful) {

                    Log.d("menuAPI : ", "${response.body()!!.drink_option.size}")
                    Log.d("menuAPI : ", "${response.body()!!.drink_option[0].name}")
                    val menu_explan = response.body()!!.menu[0]
                    val drink = response.body()!!.drink_option
                    val dessert = response.body()!!.dessert_option
                    binding.information.text = menu_explan.inform

                    addRadioButton(drink.size, binding.drinkRadioGroup, drink)
                    addRadioButton(dessert.size, binding.dessertRadioGroup, dessert)

                } else {
                    Log.d("menuAPI Fail: ", "로딩 실패")
                }
            }

            override fun onFailure(call: Call<MenuData>, t: Throwable) {
                Log.d("menuAPI Fail: ", "${t.message}")
            }

        })
    }
}