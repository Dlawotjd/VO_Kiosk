package com.vo.vo_kiosk.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.databinding.FragmentGuideLineBinding

class GuildLineFragment : Fragment() {

    private var _binding : FragmentGuideLineBinding? = null
    private val binding get() = _binding!!
    private lateinit var pagerAdapter: GuideLineAdapter
    private lateinit var indicatorImageViews: Array<ImageView>

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            updateIndicator(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGuideLineBinding.inflate(inflater, container, false)

//      pageView Adapter 부분
        pagerAdapter = GuideLineAdapter(this)
        binding.viewpager2.adapter = pagerAdapter

        binding.viewpager2.registerOnPageChangeCallback(pageChangeCallback)

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_guildLineFragment_to_choiceMenuFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class GuideLineAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        private val pageIds = arrayOf(R.layout.fragment_guideline_1, R.layout.fragment_guideline_2, R.layout.fragment_guideline_3)

        override fun getItemCount(): Int = pageIds.size

        override fun createFragment(position: Int): Fragment {
            return GuideFragment.newInstance(pageIds[position])
        }
    }

    private fun updateIndicator(position: Int) {
        for (i in 0 until binding.indicatorLayout.childCount) {
            val imageView = binding.indicatorLayout.getChildAt(i) as ImageView
            if (i == position) {
//              Indicator 선택된 부분
                imageView.setImageResource(R.drawable.indicator_current_page)
            } else {
//              Indicator 선택 안된 부분
                imageView.setImageResource(R.drawable.indicator_other)
            }
        }
    }
}