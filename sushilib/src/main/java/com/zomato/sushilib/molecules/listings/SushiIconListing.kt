package com.zomato.sushilib.molecules.listings

import android.content.Context
import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.molecules.tags.SushiCircleIconView
import com.zomato.sushilib.atoms.textviews.SushiTextView

/**
 * Created by prempal on 2019-04-30.
 */
open class SushiIconListing @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr, defStyleRes) {

    private var circleIconView = SushiCircleIconView(context)
    private var labelTextView = SushiTextView(context)
    private var textView = SushiTextView(context)

    var text: String?
        get() = textView.text.toString()
        set(value) {
            textView.text = value
        }

    var labelText: String?
        get() = labelTextView.text.toString()
        set(value) {
            labelTextView.text = value
        }

    var iconChar: String?
        get() = circleIconView.text.toString()
        set(value) {
            circleIconView.text = value
        }

    var iconColor: ColorStateList
        get() = circleIconView.textColors
        set(value) {
            circleIconView.setTextColor(value)
        }

    init {
        setupView()
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiIconListing,
            defStyleAttr, defStyleRes
        ).let {
            text = it.getString(R.styleable.SushiIconListing_text)
            labelText = it.getString(R.styleable.SushiIconListing_labelText)
            iconChar = it.getString(R.styleable.SushiIconListing_iconChar)
            iconColor = it.getColorStateList(R.styleable.SushiIconListing_iconColor)?.let { it }
                ?: ColorStateList.valueOf(
                    it.getColor(
                        R.styleable.SushiIconListing_iconColor,
                        ContextCompat.getColor(context, R.color.sushi_grey_200)
                    )
                )
            it.recycle()
        }
    }

    private fun setupView() {
        orientation = LinearLayout.HORIZONTAL
        labelTextView.setTextAppearance(R.style.TextAppearance_Sushi_Label)
        addView(circleIconView)
        val linearLayout = LinearLayout(context)
        linearLayout.apply {
            orientation = LinearLayout.VERTICAL
            val params = MarginLayoutParams(
                MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = resources.getDimensionPixelOffset(R.dimen.sushi_spacing_micro)
            }
            addView(labelTextView, params)
            addView(textView, params)
        }
        addView(
            linearLayout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                leftMargin = resources.getDimensionPixelOffset(R.dimen.sushi_spacing_macro)
            }
        )
    }
}