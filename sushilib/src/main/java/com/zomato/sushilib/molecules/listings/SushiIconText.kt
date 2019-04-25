package com.zomato.sushilib.molecules.listings

import android.content.Context
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.drawables.SushiIconDrawable
import com.zomato.sushilib.atoms.textviews.SushiTextView

open class SushiIconText @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : SushiTextView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        context?.let {
            val icon = SushiIconDrawable.Builder(context)
                .setIconStringRes(R.string.icon_filled_star)
                .setIconSize(textSize.toInt())
                .build()
            setCompoundDrawables(icon, null, null, null)
            compoundDrawablePadding = resources.getDimensionPixelSize(R.dimen.sushi_spacing_between_large)
        }

    }
}