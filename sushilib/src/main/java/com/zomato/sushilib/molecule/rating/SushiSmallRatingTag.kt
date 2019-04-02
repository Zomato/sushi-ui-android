package com.zomato.sushilib.molecule.rating

import android.content.Context
import android.util.AttributeSet
import com.zomato.sushilib.R

class SushiSmallRatingTag : SushiBigRatingTag {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(
        context,
        attributeSet,
        0,
        R.style.SushiTheme_SmallRatingTag
    )
}