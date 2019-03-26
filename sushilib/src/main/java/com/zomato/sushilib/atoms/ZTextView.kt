package com.zomato.sushilib.atoms

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.text.TextFormatUtils

open class ZTextView : TextView {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) :
            this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            this(context, attrs, defStyleAttr, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.ZTextView,
            defStyleAttr,
            R.style.SushiTheme
        )?.let {
            val zTextSize = it.getInt(R.styleable.ZTextView_zTextSize, 500)
            textSize = TextFormatUtils.zTextSizeToAndroidSp(zTextSize)

            val zFontWeight = it.getInt(R.styleable.ZTextView_zFontWeight, 500)
            typeface = TextFormatUtils.zFontWeightToTypeface(context, zFontWeight)

            it.recycle()
        }
    }
}