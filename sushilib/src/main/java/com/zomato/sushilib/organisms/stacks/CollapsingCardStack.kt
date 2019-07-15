package com.zomato.sushilib.organisms.stacks

import android.content.Context
import android.os.Handler
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView

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

    fun setAdjustedPosition(parent: ViewGroup) {
        val screenPos = IntArray(2)
        val rvScreenPos = IntArray(2)
        parent.getLocationOnScreen(rvScreenPos)
        getLocationOnScreen(screenPos)

        if (screenPos[1] < rvScreenPos[1]) {
            for (i in 0 until childCount) {
                val transY =
                    (childCount - i - 1) * (1.0f / (childCount - 1)) * (rvScreenPos[1] - screenPos[1]).toFloat()

                if (rvScreenPos[1] - screenPos[1] < height / 2) {
                    getChildAt(i).translationY = transY
                }
            }

        } else {
            for (i in 0 until childCount) {
                getChildAt(i).translationY = 0f
            }
        }
    }

    private var parentView: ViewGroup? = null

    private val simpleGestureDetector = object : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            parentView?.let { setAdjustedPosition(it) }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            parentView?.let { setAdjustedPosition(it) }
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            setAdjustedPosition(recyclerView)
            super.onScrolled(recyclerView, dx, dy)
        }
    }

    private val gestureDetector = GestureDetector(context, simpleGestureDetector)
    private val touchListener = OnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            Handler().postDelayed({
                parentView?.let { setAdjustedPosition(it) }
            }, 200)
        }
        gestureDetector.onTouchEvent(event)
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        parent?.let {
            if (it is RecyclerView) {
                parentView = it
                it.addOnScrollListener(scrollListener)
            } else if (it.parent is ScrollView || it.parent is NestedScrollView) {
                parentView = it.parent as ViewGroup?
                (it.parent as ViewGroup).setOnTouchListener(touchListener)
            }

        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        parentView = null
        parent.let {
            if (it is RecyclerView) {
                it.removeOnScrollListener(scrollListener)
            }
        }

    }

}