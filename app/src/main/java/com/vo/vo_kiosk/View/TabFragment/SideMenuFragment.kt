package com.vo.vo_kiosk.View.TabFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.SideMenuViewModel

class SideMenuFragment : Fragment() {

    companion object {
        fun newInstance() = SideMenuFragment()
    }

    private lateinit var viewModel: SideMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_side_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SideMenuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}