package com.vo.vo_kiosk.View.TabFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.TotalMenuViewModel

class TotalMenuFragment : Fragment() {

    private lateinit var viewModel: TotalMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_total_menu, container, false)
    }

}