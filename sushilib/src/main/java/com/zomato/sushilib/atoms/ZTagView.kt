package com.zomato.sushilib.atoms

import android.content.Context
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiTextView
import com.zomato.sushilib.atoms.views.RoundedView
import com.zomato.sushilib.atoms.views.ZViewOutlineProvider

class ZTagView : SushiTextView, RoundedView {
    override val imageOutlineProvider: ZViewOutlineProvider = ZViewOutlineProvider()

    constructor(context: Context?) : super(context, null) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(
        context: Context?,
        attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
    ) {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.ZTagView,
            defStyleAttr,
            defStyleRes
        )?.let {
            val cr = it.getDimension(R.styleable.ZTagView_zCornerRadius, 0F)
            if (cr != 0f) {
                cornerRadius = cr
            }
            it.recycle()
        }
    }

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        // todo
    }

    // corner radius
    // vertical - horizontal padding
    // fill/ line
    // stroke color
}