package com.zomato.sushilib.organisms.stacks.page

/**
 * Implement this to receive state callbacks for [ExpandablePageLayout].
 */
interface PageStateChangeCallbacks {

    fun onPageAboutToExpand(expandAnimDuration: Long)

    fun onPageExpanded()

    fun onPageAboutToCollapse(collapseAnimDuration: Long)

    fun onPageCollapsed()
}
