package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vo.vo_kiosk.Adapter.QRAdapter
import com.vo.vo_kiosk.ViewModel.ChoiceMenuViewModel
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.ShareQRViewModel
import com.vo.vo_kiosk.databinding.FragmentChoiceMenuBinding

class MainFragment : Fragment() {

    private var _binding : FragmentChoiceMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ChoiceMenuViewModel
    private lateinit var shareQRViewModel : ShareQRViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChoiceMenuBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ChoiceMenuViewModel::class.java]
        shareQRViewModel = ViewModelProvider(requireActivity())[ShareQRViewModel::class.java]

        binding.clickConstrain.visibility = View.VISIBLE
//        binding.voiceCardView.visibility = View.VISIBLE

        binding.clickCardView.setOnClickListener {
            findNavController().navigate(R.id.action_choiceMenuFragment_to_clickMenuFragment)
        }
        binding.voiceCardView.setOnClickListener {
            findNavController().navigate(R.id.action_choiceMenuFragment_to_menuOrderFragment)
        }

        shareQRViewModel.qrData.observe(viewLifecycleOwner) { dataList ->
            val qrPagerAdapter = QRAdapter(dataList)
            binding.bottomSheet.qrViewPager.adapter = qrPagerAdapter
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}