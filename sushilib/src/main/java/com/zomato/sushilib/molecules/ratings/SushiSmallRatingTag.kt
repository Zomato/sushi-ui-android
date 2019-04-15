package com.zomato.sushilib.molecules.ratings

import android.content.Context
import android.util.AttributeSet
import com.zomato.sushilib.R

class SushiSmallRatingTag @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.smallRatingTagStyle,
    defStyleRes: Int = R.style.SushiTheme_SmallRatingTag
) : SushiBigRatingTag(context, attrs, defStyleAttr, defStyleRes) {

    init {

    }
}