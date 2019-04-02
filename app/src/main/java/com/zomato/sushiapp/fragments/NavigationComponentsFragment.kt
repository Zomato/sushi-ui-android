package com.zomato.sushiapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R
import com.zomato.sushilib.templates.navigation.SushiBottomNavigationBar

class NavigationComponentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_nav_components, container, false)
        val tabDataList = ArrayList<SushiBottomNavigationBar.TabViewData>()
        for (i in 1..5) {
            tabDataList.add(
                SushiBottomNavigationBar.TabViewData(
                    "Menu $i", resources.getString(R.string.icon_unfilled_star),
                    resources.getString(R.string.icon_unfilled_star),
                    resources.getColor(R.color.sushi_black),
                    resources.getColor(R.color.sushi_grey_500),
                    resources.getColor(R.color.sushi_red_600),
                    resources.getColor(R.color.sushi_grey_500)
                )
            )
        }
        v.findViewById<SushiBottomNavigationBar>(R.id.bottom_nav_bar)?.setData(tabDataList)
        return v
    }
}