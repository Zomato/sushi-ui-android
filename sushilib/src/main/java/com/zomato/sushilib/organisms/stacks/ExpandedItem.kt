package com.zomato.sushilib.organisms.stacks

import android.graphics.Rect
import android.view.View

/** Details of the currently expanded item. */
data class ExpandedItem(
    // Original location of the currently expanded item (that is, when the user
    // selected this item). Can be used for restoring states after collapsing.
    val expandedItemLocationRect: Rect,
    val itemViewComingFrom: View?
    ) {

    internal fun isEmpty(): Boolean {
        return expandedItemLocationRect.width() == 0 && expandedItemLocationRect.height() == 0
    }

    companion object {
        internal val EMPTY = ExpandedItem(Rect(0, 0, 0, 0), null)
    }
}