package com.zomato.sushilib.templates.navigation

import android.content.Context
import android.support.annotation.IntegerRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.util.AttributeSet
import android.view.MenuItem
import com.zomato.sushilib.atoms.menu.SushiMenuItem

import java.util.Collections

class SushiBottomNavigationView : BottomNavigationView {

    private val sushiMenuItems = arrayListOf<SushiMenuItem>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //TODO Add IntRange
    @Throws(IllegalArgumentException::class)
    fun setMenu(sushiMenuItems: List<SushiMenuItem>, supportFragmentManager: FragmentManager, containerMain: Int) {
        this.sushiMenuItems.clear()
        this.sushiMenuItems.addAll(sushiMenuItems)
        if (sushiMenuItems.size >= 5) {
            throw IllegalArgumentException("Can't set more than 5 tabs")
        }

        for (item in sushiMenuItems) {
            this.menu.add(item.groupId, item.itemId, item.order, item.title).setIcon(item.drawableId)
        }
        setupNavigation(supportFragmentManager, containerMain)
        setStartDestination(sushiMenuItems[0].itemId)
    }

    /**
     * Set a default selected item
     */
    fun setStartDestination(startDestination: Int) {
        this.selectedItemId = startDestination
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
