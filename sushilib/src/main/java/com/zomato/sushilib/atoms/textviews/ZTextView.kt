package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.text.TextFormatUtils

open class ZTextView : TextView {

    // todo sp, dp based theme
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
            defStyleRes
        )?.let {
            val zTextSize = it.getInt(R.styleable.ZTextView_zTextSize, 500)
            val zFontWeight = it.getInt(R.styleable.ZTextView_zFontWeight, 500)

            if (!isInEditMode) {
                typeface = TextFormatUtils.zFontWeightToTypeface(context, zFontWeight)
                setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    TextFormatUtils.zTextSizeToAndroidPx(context, zTextSize))
            } else {
                // TODO: See if we can load font variants in Android Studio
                textSize = TextFormatUtils.zTextSizeToAndroidPx(null, zTextSize)
            }

            it.recycle()
        }
    }

    // todo implement this, to get custom attr from style
    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
    }
}