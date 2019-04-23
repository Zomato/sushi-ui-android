package com.zomato.sushilib.templates.navigation

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.IntRange
import android.support.annotation.Size
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.util.AttributeSet
import com.zomato.sushilib.atoms.menu.SushiMenuItem

class SushiBottomNavigationView : BottomNavigationView {

    private val sushiMenuItems = arrayListOf<SushiMenuItem>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * Sets up the navigation menu based on a supplied list of menu items
     * If you wish to handle fragment transitions yourself, don't pass the fragmentManager and mainContainerId
     */
    @Throws(IllegalArgumentException::class)
    fun setMenu(@Size(min=1, max=4) sushiMenuItems: List<SushiMenuItem>, supportFragmentManager: FragmentManager, @IdRes mainContainerId: Int, defaultMenuIndex: Int = 0) {
        if (sushiMenuItems.size >= 5) {
            throw IllegalArgumentException("Can't set more than 5 tabs")
        }

        this.sushiMenuItems.clear()
        this.sushiMenuItems.addAll(sushiMenuItems)
        for (item in sushiMenuItems) {
            this.menu.add(item.groupId, item.itemId, item.order, item.title).setIcon(item.drawableId)
        }
        setupNavigation(supportFragmentManager, mainContainerId)
        setStartDestination(sushiMenuItems[defaultMenuIndex].itemId)
    }

    /**
     * Set a default selected item represented by a menuItem.itemId
     */
    private fun setStartDestination(@IdRes startDestinationId: Int) {
        this.selectedItemId = startDestinationId
    }

    private fun setupNavigation(supportFragmentManager: FragmentManager, containerMain: Int) {
        this.setOnNavigationItemSelectedListener{ menuItem ->
            this.sushiMenuItems.forEach { sushiMenuItem ->
                if (sushiMenuItem.itemId == menuItem.itemId) {
                    val tag = sushiMenuItem.fragment?.tag
                    val fragment = sushiMenuItem.fragment
                    fragment?.let {
                        supportFragmentManager.beginTransaction()
                            .replace(containerMain, it, tag)
                            .commit()
                    }
                }
            }
            true
        }
    }
}
