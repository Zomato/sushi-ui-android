package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R

class SushiIcon : TextView {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attributeSet: AttributeSet?) : super(
        context,
        attributeSet,
        R.attr.sushiIconAppearance
    )
}