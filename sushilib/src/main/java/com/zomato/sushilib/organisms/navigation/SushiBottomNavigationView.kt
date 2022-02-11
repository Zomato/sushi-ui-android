package com.zomato.sushilib.organisms.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.Menu
import androidx.annotation.IdRes
import androidx.annotation.Size
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.menu.SushiMenuItem

open class SushiBottomNavigationView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.bottomNavigationStyle
) : BottomNavigationView(ctx, attrs, defStyleAttr) {

    init {
        context.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiBottomNavigationView,
            defStyleAttr, 0
        )?.let {
            // Resetting this as we do not want to use the icon tint list that BottomNavigationView internally sets.
            itemIconTintList = it.getColorStateList(R.styleable.SushiBottomNavigationView_itemIconTint)
            it.recycle()
        }
    }

    /**
     * Sets up the navigation menu based on a supplied list of menu items.
     *
     * @param sushiMenuItems List of [SushiMenuItem]
     * @param defaultMenuItemIndex Index of default selected menu item. Default value is 0.
     */
    @Throws(IllegalArgumentException::class)
    fun setMenu(@Size(min = 1, max = 5) sushiMenuItems: List<SushiMenuItem>, defaultMenuItemIndex: Int = 0) {
        if (sushiMenuItems.size > 5) {
            throw IllegalArgumentException("Can't set more than 5 tabs")
        }

        for (item in sushiMenuItems) {
            this.menu.add(Menu.NONE, item.itemId, Menu.NONE, item.title).also {
                item.drawable?.run {
                    it.icon = this
                } ?: item.drawableId?.run {
                    it.setIcon(this)
                }
            }
        }
        setStartDestination(sushiMenuItems[defaultMenuItemIndex].itemId)
    }

    private fun setStartDestination(@IdRes startDestinationId: Int) {
        this.selectedItemId = startDestinationId
    }
}
