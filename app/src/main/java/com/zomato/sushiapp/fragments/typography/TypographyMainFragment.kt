package com.zomato.sushiapp.fragments.typography


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R
import kotlinx.android.synthetic.main.fragment_typography.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TypographyMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_typography, container, false)

        rootView.viewpager_font_weight.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(pos: Int): Fragment =
                TypographyStyleFragment.newInstance(pos)

            override fun getCount(): Int = 5

            override fun getPageTitle(pos: Int): CharSequence? =
                TypographyStyleFragment.getTitle(pos)

        }
        return rootView
    }


}
