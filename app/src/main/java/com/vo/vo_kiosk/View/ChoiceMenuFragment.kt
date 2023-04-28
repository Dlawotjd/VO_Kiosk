package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vo.vo_kiosk.Adapter.QRAdapter
import com.vo.vo_kiosk.ViewModel.ChoiceMenuViewModel
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.ShareQRViewModel
import com.vo.vo_kiosk.databinding.FragmentChoiceMenuBinding

class ChoiceMenuFragment : Fragment() {

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

        binding.cardView1.setOnClickListener {
            findNavController().navigate(R.id.action_choiceMenuFragment_to_mainVoiceFragment)
        }
        shareQRViewModel.qrData.observe(viewLifecycleOwner) { dataList ->
            val qrPagerAdapter = QRAdapter(dataList)
            binding.qrViewPager.adapter = qrPagerAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        shareQRViewModel.qrData.observe(viewLifecycleOwner) { dataList ->
//            qrAdapter.submitList(dataList)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}