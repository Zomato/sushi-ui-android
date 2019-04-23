package com.zomato.sushilib.atoms.views

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.Keep
import android.support.v4.view.ActionProvider
import android.support.v7.view.menu.MenuBuilder
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.drawables.SushiIconDrawable

/**
 * created by championswimmer on 11/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@Keep
class SushiIconActionProvider(context: Context) : ActionProvider(context) {
    override fun onCreateActionView(): View? {
        Log.e("SUSHI", "Attempting to create SushiIconActionProvider view without MenuItem")
        // DANGER: We really have nothing to do if this is called without a menu item
        return null
    }

    /**
     * TODO
     * For Reference: https://stablekernel.com/using-custom-views-as-menu-items/
     *
     * @param forItem MenuItem supplied to create this
     */
    @SuppressLint("RestrictedApi")
    override fun onCreateActionView(forItem: MenuItem?): View? {
        return forItem?.let {
            if (TextUtils.isEmpty(it.title)) {
                throw object : IllegalArgumentException("MenuItem has no title") {}
            }
            val actionItemSize = context.resources.getDimensionPixelSize(R.dimen.sushi_action_item_size)

            // Action Item Layout
            FrameLayout(context, null, 0, R.style.Widget_AppCompat_ActionButton).apply {
                // Size = 48dp x 48dp
                layoutParams = FrameLayout.LayoutParams(actionItemSize, actionItemSize)

                // ImageView
                addView(ImageView(context).apply {
                    // 20dp, and center
                    layoutParams = FrameLayout.LayoutParams(
                        WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER
                    )
                    // Sushi Icon Drawable in it
                    setImageDrawable(
                        SushiIconDrawable.Builder(context)
                            .setIconSizeRes(R.dimen.sushi_action_item_drawable_size)
                            .setIconChar(forItem.title as String)
                            .build()
                    )
                })
                setOnClickListener {
                    /*
                     * DANGER: We reflect into MenuItem to get access to Menu
                     */
                    try {
                        val mMenuField = forItem.javaClass.getDeclaredField("mMenu")
                        mMenuField.isAccessible = true
                        val parentMenu = mMenuField.get(forItem) as MenuBuilder
                        parentMenu.performItemAction(forItem, 0)
                    } catch (e: Throwable) {
                        Log.e("SUSHI", "Could not trigger menu item click", e)
                    }
                }


            }
        }
    }

}