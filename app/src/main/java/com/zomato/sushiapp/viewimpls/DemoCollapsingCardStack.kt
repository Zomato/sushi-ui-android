package com.zomato.sushiapp.viewimpls

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.support.annotation.Px
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.*
import com.zomato.sushilib.organisms.stacks.CollapsingCardStack
import com.zomato.sushilib.organisms.stacks.ExpandedItem
import com.zomato.sushilib.organisms.stacks.InternalPageCallbacks
import com.zomato.sushilib.organisms.stacks.page.ExpandablePageLayout
import com.zomato.sushilib.utils.view.ViewUtils

/**
 * created by championswimmer on 2019-07-15
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class DemoCollapsingCardStack @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : CollapsingCardStack(context, attrs, defStyleAttr),
    InternalPageCallbacks by InternalPageCallbacks.NoOp() {

    /** Details about the currently expanded item. */
    var expandedItem: ExpandedItem =
        ExpandedItem.EMPTY

    lateinit var page: ExpandablePageLayout
        private set

    /**
     * Set the [ExpandablePageLayout] to be used with this list.
     * The pull-to-collapse threshold is set to 75% of the standard toolbar height.
     */
    fun setExpandablePage(page: ExpandablePageLayout) {
        setExpandablePage(page, (ViewUtils.run { toolbarHeight * 0.8F }).toInt())
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

    override fun onPagePull(deltaY: Float, translationY: Float) {
        page.alpha = 1f - (translationY / page.height)
    }

    override fun onPageRelease(collapseEligible: Boolean) {
        if (collapseEligible) {
            collapse()
        }
    }

}