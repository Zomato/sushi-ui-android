package com.zomato.sushilib.molecule.listing

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zomato.sushilib.R

/**
 * General Listing / Type 3
 */
class ZLabelDualTextView : LinearLayout {
    private val dualTextImpl: DualTextImpl

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        R.style.SnippetBaseVerticalPaddingTheme
    )

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attributeSet, defStyleAttr, defStyleRes) {
        orientation = VERTICAL
        setBackgroundColor(resources.getColor(R.color.sushi_white))
        dualTextImpl = DualTextImpl(this, R.layout.layout_label_dual_text, attributeSet, defStyleAttr, defStyleRes)
    }
}