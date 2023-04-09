package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vo.vo_kiosk.ViewModel.ChoiceMenuViewModel
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.databinding.FragmentChoiceMenuBinding

class ChoiceMenuFragment : Fragment() {

    private var _binding : FragmentChoiceMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ChoiceMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChoiceMenuBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ChoiceMenuViewModel::class.java)

        binding.cardView2.setOnClickListener {
            findNavController().navigate(R.id.action_choiceMenuFragment_to_mainVoiceFragment)
        }

        return binding.root
    }

}