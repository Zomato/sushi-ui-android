package com.zomato.sushiapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R
import com.zomato.sushilib.templates.navigation.SushiBottomNavigationBar
import com.zomato.sushilib.utils.theme.ResourceThemeResolver
import kotlinx.android.synthetic.main.fragment_nav_components.*

class NavigationComponentsFragment : Fragment(), SushiBottomNavigationBar.TabViewDataProvider {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nav_components, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_nav_bar.setup(this)
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getTabData(position: Int): SushiBottomNavigationBar.TabViewData {
        Log.d("TEXT COLOR PRIMARY", ResourceThemeResolver.getThemedColorFromAttr(
            context!!,
            android.R.attr.textColorPrimary
        ).toString())
        Log.d("TEXT COLOR SECONDARY", ResourceThemeResolver.getThemedColorFromAttr(
            context!!,
            android.R.attr.textColorSecondary
        ).toString())

        return SushiBottomNavigationBar.TabViewData(
            "Menu $position", resources.getString(R.string.icon_unfilled_star),
            resources.getString(R.string.icon_unfilled_star),
            position,
            ResourceThemeResolver.getThemedColorFromAttr(
                context!!,
                android.R.attr.textColorPrimary
            ),
            ResourceThemeResolver.getThemedColorFromAttr(
                context!!,
                android.R.attr.textColorSecondary
            ),
            ResourceThemeResolver.getThemedColorFromAttr(
                context!!,
                android.R.attr.colorPrimary
            ),
            ResourceThemeResolver.getThemedColorFromAttr(
                context!!,
                android.R.attr.textColorSecondary
            )
//            resources.getColor(R.color.sushi_black),
//            resources.getColor(R.color.sushi_grey_500),
//            resources.getColor(R.color.sushi_red_600),
//            resources.getColor(R.color.sushi_grey_500)
        )
    }
}