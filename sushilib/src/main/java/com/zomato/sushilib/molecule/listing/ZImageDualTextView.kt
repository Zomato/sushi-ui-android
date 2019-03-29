package com.zomato.sushilib.molecule.listing

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.ZTextView

/**
 * General Listing / Type 1
 */
class ZImageDualTextView : LinearLayout {
    private var imageDualTextImpl: ImageDualTextImpl

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(
        context,
        attributeSet,
        0,
        R.style.SnippetBaseVerticalPaddingTheme
    ) {
        orientation = HORIZONTAL
        setBackgroundColor(resources.getColor(R.color.sushi_white))
        imageDualTextImpl = ImageDualTextImpl(
            this,
            R.layout.layout_image_dual_text,
            attributeSet,
            0,
            R.style.SnippetBaseVerticalPaddingTheme
        )
    }
}