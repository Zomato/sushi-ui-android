package com.zomato.sushilib.organisms.stacks

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

/**
 * created by championswimmer on 2019-07-15
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class CollapsingCardStack @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val TAG = "CCS"

    init {
        orientation = VERTICAL
    }

    fun setAdjustedPosition(recyclerView: RecyclerView) {
        val screenPos = IntArray(2)
        val rvScreenPos = IntArray(2)
        recyclerView.getLocationOnScreen(rvScreenPos)
        getLocationOnScreen(screenPos)

        if (screenPos[1] < rvScreenPos[1]) {
            Log.d(TAG, "We are out")
            for (i in 0 until childCount) {
                val transY =
                    (childCount - i - 1) * (1.0f / (childCount - 1)) * (rvScreenPos[1] - screenPos[1]).toFloat()

                if (rvScreenPos[1] - screenPos[1] < height / 2) {
                    getChildAt(i).translationY = transY
                }
            }

        } else {
            Log.d(TAG, "We are in")
            for (i in 0 until childCount) {
                getChildAt(i).translationY = 0f
            }
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            setAdjustedPosition(recyclerView)
            super.onScrolled(recyclerView, dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_SETTLING) {

                Handler().postDelayed({
                    setAdjustedPosition(recyclerView)
                }, 100)
            }
            super.onScrollStateChanged(recyclerView, newState)
        }
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow");

        parent?.let {
            if (it is RecyclerView) {
                it.addOnScrollListener(scrollListener)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow");
        parent?.let {
            if (it is RecyclerView) {
                it.removeOnScrollListener(scrollListener)
            }
        }
    }

}