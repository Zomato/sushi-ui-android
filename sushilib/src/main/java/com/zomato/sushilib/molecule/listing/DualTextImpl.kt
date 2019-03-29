package com.zomato.sushilib.molecule.listing

import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.ViewGroup
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.ZTextView
import com.zomato.sushilib.utils.inflate

open class DualTextImpl(
    parent: ViewGroup,
    @LayoutRes layoutId: Int,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) {
    private val title: ZTextView?
    private val subtitle: ZTextView?

    init {
        val v = parent.inflate(layoutId, true)
        title = v.findViewById(R.id.title)
        subtitle = v.findViewById(R.id.subtitle)
    }
}