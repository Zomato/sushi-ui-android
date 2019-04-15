package com.zomato.sushilib.molecules.ratings

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.tagviews.SushiTagView


open class SushiBigRatingTag @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.SushiTheme_BigRatingTag
) : SushiTagView(context, attrs, defStyleAttr, defStyleRes), SushiRatingViewSetter {

    private val sushiRatingViewImpl: SushiRatingViewImpl = SushiRatingViewImpl(this, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        sushiRatingViewImpl.dispatchDraw(canvas)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        sushiRatingViewImpl.dispatchSelected(selected)
    }

    override fun setBaseColor(color: Int) {
        sushiRatingViewImpl.setBaseColor(color)
    }


    override fun shouldShowText(showText: Boolean) {
        sushiRatingViewImpl.shouldShowText(showText)
    }

    override fun setRatingNumber(rating: Int) {
        sushiRatingViewImpl.setRatingNumber(rating)
    }


}