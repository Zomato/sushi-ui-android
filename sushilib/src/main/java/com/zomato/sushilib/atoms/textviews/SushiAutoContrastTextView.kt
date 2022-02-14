package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.color.ColorContrastUtils

/**
 * A Text view that uses white or black color depending on
 * the background color
 */

open class SushiAutoContrastTextView @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : SushiTextView(ctx, attrs, defStyleAttr, defStyleRes) {


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // TODO: This should also react when background changes
        setAutoContrastColor()
    }

    private fun setAutoContrastColor() {
        if (background is ColorDrawable) {
            if (ColorContrastUtils.isDarkColor((background as ColorDrawable).color)) {
                setTextColor(resources.getColor(R.color.sushi_grey_100))
            } else {
                setTextColor(resources.getColor(R.color.sushi_black))
            }
        }
    }
}