package com.zomato.sushilib.organisms.stacks

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Handler
import android.support.annotation.Px
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.*
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import android.widget.ScrollView
import com.zomato.sushilib.organisms.stacks.page.ExpandablePageLayout
import com.zomato.sushilib.utils.dimens.DimenUtils.dp2px

/**
 * created by championswimmer on 2019-07-15
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class CollapsingCardStack @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), InternalPageCallbacks {

    val TAG = "CCS"

    /** Details about the currently expanded item. */
    var expandedItem: ExpandedItem = ExpandedItem.EMPTY

    lateinit var page: ExpandablePageLayout
        private set

    /**
     * Set the [ExpandablePageLayout] to be used with this list.
     * The pull-to-collapse threshold is set to 75% of the standard toolbar height.
     */
    fun setExpandablePage(page: ExpandablePageLayout) {
        setExpandablePage(page, (Views.toolbarHeight(context) * 0.8F).toInt())
    }

    /**
     * Set the [ExpandablePageLayout] to be used with this list.
     * @param collapseDistanceThreshold Minimum Y-distance the page has to be pulled before it's eligible for collapse.
     */
    fun setExpandablePage(page: ExpandablePageLayout, @Px collapseDistanceThreshold: Int) {
        setExpandablePageInternal(page)
        page.pullToCollapseThresholdDistance = collapseDistanceThreshold
    }

    /**
     * @param itemId ID of the item to expand.
     */
    @JvmOverloads
    fun expandItem(itemView: View) {

        if (isLaidOut.not()) {
            post { expandItem(itemView) }
            return
        }

        if (page.isExpandedOrExpanding) {
            return
        }

        val itemRect = Rect(
            left + itemView.left,
            top + itemView.top,
            width - right + itemView.right,
            top + itemView.bottom
        )

        expandedItem = ExpandedItem(itemRect, itemView)
        page.setBackgroundColor(
            (itemView.background as ColorDrawable).color
        )
        page.expand(expandedItem)
    }

    fun collapse() {
        if (page.isCollapsedOrCollapsing.not()) {
            page.collapse(expandedItem)
        }
    }

    private fun setExpandablePageInternal(expandablePage: ExpandablePageLayout) {
        page = expandablePage
        expandablePage.internalStateCallbacksForRecyclerView = this
    }

    init {
        orientation = VERTICAL
    }

    /**
     * Moves the cards downwards when this view is scrolled up (and vice versa)
     * to provide the stacking up of cards effect.
     *
     * @param parent The container inside which the [CollapsingCardStack] is
     */
    fun setAdjustedPosition(parent: ViewGroup) {
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

    private val rvScrollListener = object : RecyclerView.OnScrollListener() {
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
    private val scrollChangeListener = ViewTreeObserver.OnScrollChangedListener {
        parentView?.let { setAdjustedPosition(it) }
    }

    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)
        child?.setOnClickListener {
            expandItem(it)
        }
    }

    override fun onPageAboutToExpand() {
        animate()
            .translationYBy(200f)
            .setDuration(250L)
            .setInterpolator(FastOutSlowInInterpolator())
            .start()
        this.apply {
            animate()
                .alpha(0f)
                .setDuration(250L)
                .setInterpolator(FastOutSlowInInterpolator())
                .start()
        }
        expandedItem.itemViewComingFrom?.apply {
            animate()
                .translationYBy(-400f)
                .setDuration(250L)
                .setInterpolator(FastOutSlowInInterpolator())
                .start()
        }
    }

    override fun onPageFullyCovered() {
    }

    override fun onPageAboutToCollapse() {
        animate()
            .translationY(0f)
            .setDuration(250L)
            .setInterpolator(FastOutSlowInInterpolator())
            .start()

        this.apply {
            animate()
                .alpha(1f)
                .setDuration(250L)
                .setInterpolator(FastOutSlowInInterpolator())
                .start()
        }
        expandedItem.itemViewComingFrom?.apply {
            animate()
                .translationYBy(400f)
                .setDuration(250L)
                .setInterpolator(FastOutSlowInInterpolator())
                .start()
        }
    }

    override fun onPageCollapsed() {
    }

    override fun onPagePull(deltaY: Float, translationY: Float) {
        page.alpha = 1f - (translationY / page.height)
    }

    override fun onPageRelease(collapseEligible: Boolean) {
        if (collapseEligible) {
            collapse()
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

}