package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.text.TextFormatUtils

open class ZTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : TextView(context, attrs) {

    init {
        context.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.ZTextView,
            defStyleAttr,
            defStyleRes
        )?.let {
            val zTextSize = it.getInt(R.styleable.ZTextView_zTextSize, 500)
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX, TextFormatUtils.zTextSizeToAndroidSp(context, zTextSize)
            )
            val zFontWeight = it.getInt(R.styleable.ZTextView_zFontWeight, 500)
            typeface = TextFormatUtils.zFontWeightToTypeface(context, zFontWeight)

            it.recycle()
        }
    }

    // todo sp, dp based theme

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        // todo
    }
}