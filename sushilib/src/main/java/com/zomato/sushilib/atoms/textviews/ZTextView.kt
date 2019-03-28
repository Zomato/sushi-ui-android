package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.graphics.Typeface
import android.support.v4.graphics.TypefaceCompat
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.text.TextFormatUtils
import org.intellij.lang.annotations.JdkConstants

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
            val zFontWeight = it.getInt(R.styleable.ZTextView_zFontWeight, 500)

            if (!isInEditMode) {
                typeface = TextFormatUtils.zFontWeightToTypeface(context, zFontWeight)
                textSize = TextFormatUtils.zTextSizeToAndroidSp(context, zTextSize)
            } else {
                textSize = TextFormatUtils.zTextSizeToAndroidSp(null, zTextSize)
                // TODO: See if we can load font variants in Android Studio
            }

            it.recycle()
        }
    }
}