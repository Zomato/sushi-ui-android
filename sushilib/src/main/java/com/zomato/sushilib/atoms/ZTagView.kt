package com.zomato.sushilib.atoms

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiTextView
import com.zomato.sushilib.atoms.views.RoundedView
import com.zomato.sushilib.atoms.views.ZViewOutlineProvider

class ZTagView : SushiTextView, RoundedView {
    override val imageOutlineProvider: ZViewOutlineProvider = ZViewOutlineProvider()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) :
            this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            this(context, attrs, defStyleAttr, resolveDefStyleRes(context?.theme, attrs))

    constructor(
        context: Context?,
        attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

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

    companion object {
        private fun resolveDefStyleRes(theme: Resources.Theme?, attrs: AttributeSet?): Int {
            val a = theme?.obtainStyledAttributes(attrs, R.styleable.ZTagView, 0, 0)
            val type = a?.getInt(R.styleable.ZTagView_zTagType, 1)
            a?.recycle()
            return getStyleForType(type)
        }

        fun getStyleForType(type: Int?): Int {
            // todo create enums
            return when (type) {
                1 -> R.style.SushiTheme_TagAppearance_RoundedTag
                2 -> R.style.SushiTheme_TagAppearance_SmallRoundedTag
                3 -> R.style.SushiTheme_TagAppearance_HighlightRoundedTag
                4 -> R.style.SushiTheme_TagAppearance_SmallHighlightRoundedTag
                5 -> R.style.SushiTheme_TagAppearance_Capsule
                6 -> R.style.SushiTheme_TagAppearance_SmallCapsule
                else -> R.style.SushiTheme_TagAppearance_RoundedTag
            }
        }
    }

    // corner radius
    // vertical - horizontal padding
    // fill/ line
    // stroke color
}