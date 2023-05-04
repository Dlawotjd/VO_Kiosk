package com.vo.vo_kiosk.View.TabFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.DesertMenuViewModel

class DesertMenuFragment : Fragment() {

    private lateinit var viewModel: DesertMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_desert, container, false)
    }

}