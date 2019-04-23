package com.zomato.sushilib.molecules.ratings

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.drawables.SushiIconDrawable
import com.zomato.sushilib.atoms.views.RoundedView

class SushiRatingViewImpl(
    val view: TextView,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : SushiRatingViewSetter {


    private val context: Context?
        get() = view.context

    private val resources: Resources?
        get() = context?.resources


    @ColorInt
    private var baseColor = resources?.getColor(R.color.sushi_green_500) ?: 0 // selected color
    @ColorInt
    private var baseTextColor = resources?.getColor(R.color.sushi_white) ?: 0 // selected text color
    @ColorInt
    private var unselectedBackgroundColor = resources?.getColor(R.color.sushi_white) ?: 0 // unselected color
    @ColorInt
    private var unselectedTextColor =
        resources?.getColor(R.color.sushi_disabled_text_color) ?: 0 // unselected text color

    private var icon: SushiIconDrawable? = null
    private var showText: Boolean = true
    private var rating: Int = 0


    private val disableBorderPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = unselectedTextColor ?: 0
        strokeWidth = resources?.getDimension(R.dimen.sushi_stoke_width_small) ?: 0f
        isAntiAlias = true
    }

    init {
        view.context?.let {
            resources?.let { r ->
                icon = SushiIconDrawable.Builder(it)
                    .setIconStringRes(R.string.icon_filled_star)
                    .setIconSize(view.textSize.toInt() - r.getDimensionPixelOffset(R.dimen.sushi_spacing_micro))
                    .setColorRes(R.color.sushi_white)
                    .build()
                view.compoundDrawablePadding = r.getDimensionPixelOffset(R.dimen.sushi_spacing_between_small)
                view.setCompoundDrawables(null, null, icon, null)
            }

        }

        context?.theme?.obtainStyledAttributes(attrs, R.styleable.SushiBigRatingTag, defStyleAttr, defStyleRes)?.apply {
            baseColor = getColor(R.styleable.SushiBigRatingTag_baseColor, baseColor)
        }
        dispatchSelected(view.isSelected)
    }


    fun dispatchDraw(canvas: Canvas?) {
        if (!view.isSelected) {
            canvas?.drawRoundRect(
                0f,
                0f,
                view.width.toFloat(),
                view.height.toFloat(),
                (view as? RoundedView)?.cornerRadius ?: 0f,
                (view as? RoundedView)?.cornerRadius ?: 0f,
                disableBorderPaint
            )
        }
    }

    fun dispatchSelected(selected: Boolean) {
        setViewState()
    }

    override fun shouldShowText(showText: Boolean) {
        this.showText = showText
        setViewState()
    }

    override fun setRatingNumber(rating: Int) {
        this.rating = rating
        view.text = rating.toString()
    }

    override fun setBaseColor(color: Int) {
        baseColor = color
        setViewState()
    }


    private fun setViewState() {
        if (view.isSelected) {
            view.setBackgroundColor(baseColor)
            (if (showText) baseTextColor else baseColor).run {
                view.setTextColor(this)
                icon?.setColor(this)
            }

        } else {
            view.setBackgroundColor(unselectedBackgroundColor)
            (if (showText) unselectedTextColor else unselectedBackgroundColor).run {
                view.setTextColor(this)
                icon?.setColor(this)
            }
        }
    }
}