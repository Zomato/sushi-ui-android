package com.zomato.sushilib.atoms.tagviews

import android.content.Context
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiTextView
import com.zomato.sushilib.atoms.views.RoundedView

open class SushiTagView @JvmOverloads constructor(
    context: Context?, attrs: AttributeSet?,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : SushiTextView(context, attrs, defStyleAttr, defStyleRes), RoundedView {

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTagView,
            defStyleAttr,
            defStyleRes
        )?.let {
            val cr = it.getDimension(R.styleable.SushiTagView_sushiCornerRadius, 0F)
            if (cr != 0f) {
                cornerRadius = cr
            }
            it.recycle()
        }
    }
//
//    override fun setTextAppearance(resId: Int) {
//        super.setTextAppearance(resId)
//        // todo
//    }

    // corner radius
    // vertical - horizontal padding
    // fill/ line
    // stroke color
}