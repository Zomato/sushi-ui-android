package com.zomato.sushiapp.fragments.buttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.zomato.sushiapp.R
import kotlinx.android.synthetic.main.fragment_buttons.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ButtonsMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView  = inflater.inflate(R.layout.fragment_buttons, container, false)
        rootView.viewpager_buttons.adapter = object : FragmentPagerAdapter(childFragmentManager) {

            override fun getItem(pos: Int): Fragment =
                ButtonStyleFragment.newInstance(pos)

            override fun getCount(): Int = 3

            override fun getPageTitle(pos: Int): CharSequence? =
                    ButtonStyleFragment.getTitle(pos)

        }
        return rootView
    }
}