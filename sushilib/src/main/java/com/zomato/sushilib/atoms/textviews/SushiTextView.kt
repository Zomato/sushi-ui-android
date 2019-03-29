package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.support.design.resources.TextAppearance
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.text.TextFormatUtils

open class SushiTextView : TextView {

    constructor(context: Context?) : super(context, null) {
        init(context, null, 0 ,0)
    }
    constructor(context: Context?, attrs: AttributeSet?) :
            super(context, attrs) {
        init(context, attrs, 0 ,0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr, 0) {
        init(context, null, defStyleAttr ,0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, null, defStyleAttr ,defStyleRes)
    }

    fun init(
        context: Context?,
        attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTextView,
            defStyleAttr,
            defStyleRes
        )?.let {
            val sushiFontWeight = it.getInt(R.styleable.SushiTextView_sushiFontWeight, -1)

            if (!isInEditMode && sushiFontWeight != -1) {
                typeface = TextFormatUtils.sushiFontWeightToTypeface(context, sushiFontWeight)
            }

            it.recycle()
        }
    }

    // todo implement this, to get custom attr from style
    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
    }
}