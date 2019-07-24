package com.zomato.sushilib.organisms.stacks.page

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import com.zomato.sushilib.organisms.stacks.ExpandedItem

class StandaloneExpandablePageLayout(
    context: Context,
    attrs: AttributeSet? = null
) : ExpandablePageLayout(context, attrs) {

    internal interface Callbacks {

        /**
         * Page has fully collapsed and is no longer visible.
         */
        fun onPageCollapsed()

        /**
         * Page was released while being pulled.
         *
         * @param collapseEligible Whether the page was pulled enough for collapsing it.
         */
        fun onPageRelease(collapseEligible: Boolean)
    }

    internal lateinit var callbacks: Callbacks

    init {
        collapsedAlpha = 1F
        animationDurationMillis = 300

        addOnPullListener(object : PullToCollapseListener.OnPullListener {
            override fun onPull(
                deltaY: Float,
                currentTranslationY: Float,
                upwardPull: Boolean,
                deltaUpwardPull: Boolean,
                collapseEligible: Boolean
            ) {

            }

            override fun onRelease(collapseEligible: Boolean) {
                callbacks.onPageRelease(collapseEligible)
            }
        })

        addStateChangeCallbacks(object : PageStateChangeCallbacks {
            override fun onPageAboutToExpand(expandAnimDuration: Long) {

            }

            override fun onPageExpanded() {

            }

            override fun onPageAboutToCollapse(collapseAnimDuration: Long) {

            }

            override fun onPageCollapsed() {
                callbacks.onPageCollapsed()
            }

        })
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if (isInEditMode) {
            expandImmediately()
            setClippedDimensions(r, b)
        }
    }

    /**
     * Expands this page with animation so that it fills the whole screen.
     *
     * @param fromShapeRect Initial dimensions of this page.
     */
    internal fun expandFrom(fromShapeRect: Rect) {
        setClippedDimensions(width, 0)
        expand(ExpandedItem(fromShapeRect, null))
    }

    /**
     * @param toShapeRect Final dimensions of this page, when it fully collapses.
     */
    internal fun collapseTo(toShapeRect: Rect) {
        collapse(ExpandedItem(toShapeRect, null))
    }

}