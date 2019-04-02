package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.text.TextFormatUtils

open class SushiTextView @JvmOverloads constructor(
    context: Context?, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : TextView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTextView,
            defStyleAttr,
            defStyleRes
        )?.let {
            val sushiFontWeight = it.getInt(R.styleable.SushiTextView_sushiFontWeight, -1)

            if (sushiFontWeight != -1) {
                setTextAppearance(TextFormatUtils.sushiFontWeightToTextAppearance(sushiFontWeight))
            }
            it.recycle()
        }
    }

    override fun setTextAppearance(resId: Int) {
        @Suppress("DEPRECATION")
        super.setTextAppearance(context, resId)
    }
}