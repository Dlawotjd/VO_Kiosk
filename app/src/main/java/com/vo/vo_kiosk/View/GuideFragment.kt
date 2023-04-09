package com.vo.vo_kiosk.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vo.vo_kiosk.R


class GuideFragment : Fragment() {

    companion object {
        private const val ARG_PAGE_LAYOUT_ID = "page_layout_id"

        fun newInstance(pageLayoutId: Int): GuideFragment {
            val args = Bundle()
            args.putInt(ARG_PAGE_LAYOUT_ID, pageLayoutId)
            val fragment = GuideFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutId = arguments?.getInt(ARG_PAGE_LAYOUT_ID) ?: 0
        return inflater.inflate(layoutId, container, false)
    }
}
