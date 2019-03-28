package com.zomato.sushilib.molecule.listing

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zomato.sushilib.R

class ZDualTextView : LinearLayout {

    var dualTextImpl: DualTextImpl? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        R.style.SnippetSidePaddingTheme
    )

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attributeSet, defStyleAttr, defStyleRes) {
        orientation = VERTICAL
        setBackgroundColor(resources.getColor(R.color.sushi_white))
        dualTextImpl = DualTextImpl(this, R.layout.layout_dual_text, attributeSet, defStyleAttr, defStyleRes)
    }
}