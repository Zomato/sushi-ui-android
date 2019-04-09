package com.zomato.sushilib.molecules.listings

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zomato.sushilib.R

/**
 * General Listing / Type 1
 */
class SushiImageDualTextView : LinearLayout {
    private var imageDualTextImpl: ImageDualTextImpl

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(
        context,
        attributeSet,
        0,
        R.style.SnippetBaseVerticalPaddingTheme
    ) {
        orientation = HORIZONTAL
        imageDualTextImpl = ImageDualTextImpl(
            this,
            R.layout.layout_image_dual_text,
            attributeSet,
            0, R.style.SnippetBaseVerticalPaddingTheme
        )
    }
}