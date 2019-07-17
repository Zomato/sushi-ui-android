package com.zomato.sushilib.utils.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.Rect
import android.graphics.RectF
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.ViewTreeObserver

/**
 * created by championswimmer on 2019-07-17
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object ViewUtils {

    /**
     * Execute a runnable when the next global layout happens for a `View`. Example usage includes
     * waiting for a list to draw its children just after you have updated its adapter's data-set.
     */
    fun View.executeOnNextLayout(listener: () -> Unit) {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                listener()
            }
        })
    }

    /**
     * Execute a runnable when a [view]'s dimensions get measured and is laid out on the screen.
     */
    fun View.executeOnMeasure(listener: () -> Unit) {
        if (isInEditMode || isLaidOut) {
            listener()
            return
        }

        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (isLaidOut) {
                    viewTreeObserver.removeOnPreDrawListener(this)
                    listener()

                } else if (visibility == View.GONE) {
                    Log.w(
                        "Views",
                        "View's visibility is set to Gone. It'll never be measured: ${resources.getResourceEntryName(id)}"
                    )
                    viewTreeObserver.removeOnPreDrawListener(this)
                }
                return true
            }
        })
    }

    fun View.globalVisibleRect(): RectF {
        val rect = Rect()
        getGlobalVisibleRect(rect)
        return RectF(rect.left.toFloat(), rect.top.toFloat(), rect.right.toFloat(), rect.bottom.toFloat())
    }

    fun ViewPropertyAnimator.withEndAction(action: (Boolean) -> Unit): ViewPropertyAnimator {
        return setListener(object : AnimatorListenerAdapter() {
            var canceled = false

            override fun onAnimationStart(animation: Animator) {
                canceled = false
            }

            override fun onAnimationCancel(animation: Animator) {
                canceled = true
            }

            override fun onAnimationEnd(animation: Animator) {
                action(canceled)
            }
        })
    }


    val View.toolbarHeight get(): Int {
        val typedArray = context.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val standardToolbarHeight = typedArray.getDimensionPixelSize(0, 0)
        typedArray.recycle()
        return standardToolbarHeight
    }

}