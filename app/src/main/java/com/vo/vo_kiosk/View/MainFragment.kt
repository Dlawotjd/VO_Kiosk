package com.vo.vo_kiosk.View

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vo.vo_kiosk.Adapter.QRAdapter
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.OrderMenuViewModel
import com.vo.vo_kiosk.ViewModel.ShareQRViewModel
import com.vo.vo_kiosk.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val faceAge = arguments?.getString("faceAge")


        if (faceAge!!.toInt() == 0) {
            binding.clickConstrain.visibility = View.VISIBLE
        } else {
            binding.voiceConstrain.visibility = View.VISIBLE
        }

        binding.clickCardView.setOnClickListener {
            findNavController().navigate(R.id.action_Main_Fragment_to_clickMenuFragment)
        }
        binding.voiceCardView.setOnClickListener {
            findNavController().navigate(R.id.action_Main_Fragment_to_menuOrderFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}