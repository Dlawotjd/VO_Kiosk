package com.vo.vo_kiosk.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vo.vo_kiosk.View.TabFragment.DesertMenuFragment
import com.vo.vo_kiosk.View.TabFragment.MainMenuFragment
import com.vo.vo_kiosk.View.TabFragment.SideMenuFragment
import com.vo.vo_kiosk.View.TabFragment.TotalMenuFragment

class TabLayoutAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int) : Fragment {
        return when (position) {
            0 -> {
                TotalMenuFragment()
            }
            1 -> {
                MainMenuFragment()
            }
            2 -> {
                SideMenuFragment()
            }
            else -> {
                DesertMenuFragment()
            }
        }
    }
}