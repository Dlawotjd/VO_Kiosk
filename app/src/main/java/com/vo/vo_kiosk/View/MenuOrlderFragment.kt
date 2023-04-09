package com.vo.vo_kiosk.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.ViewModel.MenuOrlderViewModel
import com.vo.vo_kiosk.R

class MenuOrlderFragment : Fragment() {

    companion object {
        fun newInstance() = MenuOrlderFragment()
    }

    private lateinit var viewModel: MenuOrlderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_orlder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuOrlderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}