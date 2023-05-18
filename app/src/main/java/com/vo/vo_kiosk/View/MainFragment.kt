package com.vo.vo_kiosk.View

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vo.vo_kiosk.Adapter.QRAdapter
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.ShareQRViewModel
import com.vo.vo_kiosk.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var shareQRViewModel : ShareQRViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val faceAge = arguments?.getString("faceAge")
        shareQRViewModel = ViewModelProvider(requireActivity())[ShareQRViewModel::class.java]

        if (faceAge!!.toInt() == 0) {
            binding.clickConstrain.visibility = View.VISIBLE
        } else {
            binding.voiceCardView.visibility = View.VISIBLE
        }

        binding.clickCardView.setOnClickListener {
            findNavController().navigate(R.id.action_Main_Fragment_to_clickMenuFragment)
        }
        binding.voiceCardView.setOnClickListener {
            findNavController().navigate(R.id.action_Main_Fragment_to_menuOrderFragment)
        }

        shareQRViewModel.qrData.observe(viewLifecycleOwner) { dataList ->
            val qrPagerAdapter = QRAdapter(dataList)
            binding.mainBottomSheet.qrViewPager.adapter = qrPagerAdapter
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}