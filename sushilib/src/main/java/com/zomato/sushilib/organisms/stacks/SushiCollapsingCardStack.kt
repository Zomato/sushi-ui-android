package com.zomato.sushilib.organisms.stacks

import android.content.Context
import android.os.Build
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.ScrollView

/**
 * A view inside which you can put other views that collapses together
 * when reaching the top of the screen scroll.
 *
 * NOTE: This works only and only as direct child of [RecyclerView] or
 * grandchild of [ScrollView] / [NestedScrollView]
 * <pre>
 *     RecyclerView
 *     └── SushiCollapsingCardStack
 *
 *     or
 *
 *     ScrollView
 *     └── <T: ViewGroup>
 *          └── SushiCollapsingCardStack
 *
 * </pre>
 *
 * created by championswimmer on 2019-07-24
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
open class SushiCollapsingCardStack @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        var instance: SushiCollapsingCardStack? = null
    }

    init {
        orientation = VERTICAL
        instance = this
    }

    private var cardStackAdapter: Adapter? = null
    private var parentView: ViewGroup? = null
    private val rvScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            setAdjustedPosition(recyclerView)
            super.onScrolled(recyclerView, dx, dy)
        }
    }
    private val scrollChangeListener = ViewTreeObserver.OnScrollChangedListener {
        parentView?.let { setAdjustedPosition(it) }
    }

    fun setAdapter(adapter: Adapter) {
        cardStackAdapter = adapter
        cardStackAdapter?.apply {
            removeAllViews()
            for (i in 0 until getItemCount()) {
                addView(getView(this@SushiCollapsingCardStack, i))
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        parent?.let { p1 ->
            if (p1 is RecyclerView) {
                parentView = p1
                p1.addOnScrollListener(rvScrollListener)
            } else {
                p1.parent.let { p2 ->
                    if (p2 is ScrollView) {
                        parentView = p2
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            p2.setOnScrollChangeListener { v, _, _, _, _ ->
                                setAdjustedPosition((v as ViewGroup))
                            }
                        } else {
                            p2.viewTreeObserver.addOnScrollChangedListener(
                                scrollChangeListener
                            )
                        }
                    } else if (p2 is NestedScrollView) {
                        parentView = p2
                        p2.setOnScrollChangeListener { nsv: NestedScrollView?, _, _, _, _ ->
                            nsv?.let { setAdjustedPosition(it) }
                        }
                    }
                }
            }

        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        parentView = null
        parent.let { p1 ->
            if (p1 is RecyclerView) {
                p1.removeOnScrollListener(rvScrollListener)
            } else {
                p1.parent.let { p2 ->
                    if (p2 is ScrollView) {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                            p2.viewTreeObserver.removeOnScrollChangedListener(scrollChangeListener)
                        }
                    }
                }
            }
        }
    }

    /**
     * Moves the cards downwards when this view is scrolled up (and vice versa)
     * to provide the stacking up of cards effect.
     *
     * @param parent The container inside which the [SushiCollapsingCardStack] is
     */
    private fun setAdjustedPosition(parent: ViewGroup) {
        val screenPos = IntArray(2)
        val rvScreenPos = IntArray(2)
        parent.getLocationOnScreen(rvScreenPos)
        getLocationOnScreen(screenPos)

        if (screenPos[1] < rvScreenPos[1]) {
            val gap = rvScreenPos[1] - screenPos[1]

            for (i in 0 until childCount) {
                val transY = ((gap.toFloat() / (childCount - 1)) * (childCount - i - 1))

                if (gap / childCount < getChildAt(i).height * 0.5) {
                    getChildAt(i).translationY = transY
                }
            }

        } else {
            for (i in 0 until childCount) {
                getChildAt(i).translationY = 0f
            }
        }
    }

    interface Adapter {
        fun getItemCount(): Int
        fun getView(parent: ViewGroup, position: Int): View
    }

}