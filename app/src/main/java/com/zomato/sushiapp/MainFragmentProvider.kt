package com.zomato.sushiapp

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.zomato.sushiapp.fragments.AboutSushiFragment
import com.zomato.sushiapp.fragments.AboutZomatoFragment
import com.zomato.sushiapp.fragments.HomeFragment
import com.zomato.sushilib.organisms.navigation.SushiBottomNavigationBar
import com.zomato.sushilib.utils.theme.ResourceThemeResolver

class MainFragmentProvider(private val context: Context, private val fm: FragmentManager) :
    SushiBottomNavigationBar.TabViewDataProvider {

    private val homeFragment by lazy { HomeFragment() }
    private val aboutZomatoFragment by lazy { AboutZomatoFragment() }
    private val aboutSushiFragment by lazy { AboutSushiFragment() }

    override fun getCount(): Int {
        return 3
    }

    override fun getTabData(position: Int): SushiBottomNavigationBar.TabViewData {
        return SushiBottomNavigationBar.TabViewData(
            getTitle(position), context.resources.getString(R.string.icon_unfilled_star),
            context.resources.getString(R.string.icon_unfilled_star),
            position,
            ResourceThemeResolver.getThemedColorFromAttr(
                context,
                android.R.attr.textColorPrimary
            ),
            ResourceThemeResolver.getThemedColorFromAttr(
                context,
                android.R.attr.textColorSecondary
            ),
            ResourceThemeResolver.getThemedColorFromAttr(
                context,
                android.R.attr.colorPrimary
            ),
            ResourceThemeResolver.getThemedColorFromAttr(
                context,
                android.R.attr.textColorSecondary
            )
        )
    }

    fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> homeFragment
            1 -> aboutZomatoFragment
            2 -> aboutSushiFragment
            else -> throw NotImplementedError()
        }
    }

    fun getTitle(position: Int): String {
        return when (position) {
            0 -> "Sushi"
            1 -> "Zomato"
            2 -> "About"
            else -> throw NotImplementedError()
        }
    }

}