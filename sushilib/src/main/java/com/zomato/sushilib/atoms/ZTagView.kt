package com.zomato.sushilib.atoms

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Outline
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.ZTextView
import com.zomato.sushilib.atoms.views.OutlineType
import com.zomato.sushilib.atoms.views.RoundedView
import com.zomato.sushilib.atoms.views.ZViewOutlineProvider

class ZTagView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ZTextView(context, attrs, defStyleAttr, defStyleRes), RoundedView {

    init {
        context.theme?.obtainStyledAttributes(
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

    override val imageOutlineProvider: ZViewOutlineProvider = ZViewOutlineProvider()

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        // todo
    }

    // corner radius
    // vertical - horizontal padding
    // fill/ line
    // stroke color
}