package com.zomato.sushilib.molecules.listings

import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.zomato.sushilib.R

class ImageDualTextImpl(
    parent: ViewGroup,
    @LayoutRes layoutId: Int,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : DualTextImpl(parent, layoutId, attributeSet, defStyleAttr, defStyleRes) {
    val image: ImageView? = parent.findViewById(R.id.image) as? ImageView

}