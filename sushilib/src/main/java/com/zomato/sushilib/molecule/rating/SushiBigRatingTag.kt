package com.zomato.sushilib.molecule.rating

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.tagviews.SushiTagView


open class SushiBigRatingTag : SushiTagView,
    SushiRatingViewSetter {

    private val sushiRatingViewImpl: SushiRatingViewImpl



    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(
        context,
        attrs,
        0,
        R.style.SushiTheme_BigRatingTag
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        sushiRatingViewImpl = SushiRatingViewImpl(this, attrs, defStyleAttr, defStyleRes)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("test_Tag", "draw called width = $width" + " 44dp = ${resources.getDimensionPixelSize(R.dimen.sushi_big_rating_min_width)}")
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