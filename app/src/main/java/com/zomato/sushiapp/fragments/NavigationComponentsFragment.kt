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

        return SushiBottomNavigationBar.TabViewData(
            "Menu $position", resources.getString(R.string.icon_unfilled_star),
            resources.getString(R.string.icon_unfilled_star),
            position,
            context?.let {ResourceThemeResolver.getThemedColorFromAttr(
                it,
                android.R.attr.textColorPrimary
            ) } ?: 0,
            context?.let {ResourceThemeResolver.getThemedColorFromAttr(
                it,
                android.R.attr.textColorSecondary
            ) } ?: 0,
            context?.let {ResourceThemeResolver.getThemedColorFromAttr(
                it,
                android.R.attr.colorAccent
            ) } ?: 0,
            context?.let {ResourceThemeResolver.getThemedColorFromAttr(
                it,
                android.R.attr.textColorSecondary
            ) } ?: 0
        )
    }
}