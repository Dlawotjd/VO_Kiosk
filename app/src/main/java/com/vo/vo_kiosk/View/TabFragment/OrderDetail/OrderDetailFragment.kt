package com.vo.vo_kiosk.View.TabFragment.OrderDetail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.vo.vo_kiosk.ViewModel.OrderDetailViewModel
import com.vo.vo_kiosk.databinding.FragmentOrderDetailBinding


class OrderDetailFragment : Fragment() {

    private var _binding : FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!
    private var sideRadioGroup : RadioGroup? = null

    private lateinit var viewModel: OrderDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)

        val value = arguments?.getString("menu")
        Log.d("budleValue", value.toString())
        binding.detailTextView.text = value
        binding.detailConstraintLayout.visibility = View.GONE

        binding.detailSet.setOnClickListener {
            binding.detailConstraintLayout.visibility = View.VISIBLE
            binding.sideRadioGroup.visibility= View.VISIBLE
        }

        binding.detailSingle.setOnClickListener {
            binding.detailConstraintLayout.visibility = View.GONE
            binding.sideRadioGroup.clearCheck()
            binding.drinkRadioGroup.clearCheck()
        }


        addRadioButton(4, binding.sideRadioGroup)
        addRadioButton(4, binding.drinkRadioGroup)

        return binding.root
    }

    fun addRadioButton(number : Int, radioGroup: RadioGroup) {
        for (i in 1..number) {
            val rdbtn = RadioButton(requireContext())
            rdbtn.id = View.generateViewId()
            rdbtn.text = "Radio " + rdbtn.id
            rdbtn.setOnClickListener {
                // 클릭 시 동작할 코드 작성
            }
            radioGroup!!.addView(rdbtn)
        }
    }
}