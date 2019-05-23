package com.zomato.sushilib.molecules.listings

import android.animation.LayoutTransition
import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.StyleRes
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiTextView

/**
 * created by championswimmer on 29/03/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 *
 * @constructor overloads all Java ones
 *
 * NOTE: @JvmOverloads is fine here as the default
 * constructors do not have any defStyleRes or defStyleAttr
 */
open class SushiTextListing @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var initialized = false

    private var labelTextView: SushiTextView? = null
    private var headlineTextView: SushiTextView? = null
    private var subtitleTextView: SushiTextView? = null
    private var bodyTextView: SushiTextView? = null

    private val itemLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
        bottomMargin = resources.getDimensionPixelSize(R.dimen.sushi_spacing_micro)
    }

    var headlineText: String?
        get() = headlineTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                headlineTextView = null
            } else {
                headlineTextView = (headlineTextView ?: SushiTextView(
                    context,
                    defStyleRes = R.style.TextAppearance_Sushi_Headline
                )).apply {
                    text = value
                    layoutParams = itemLayoutParams
                }
            }
            relayout()
        }

    var subtitleText: String?
        get() = subtitleTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                subtitleTextView = null
            } else {
                subtitleTextView = (subtitleTextView ?: SushiTextView(
                    context,
                    defStyleRes = R.style.TextAppearance_Sushi_Subtitle
                )).apply {
                    text = value
                    layoutParams = itemLayoutParams
                }
            }
            relayout()
        }

    var bodyText: String?
        get() = bodyTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                bodyTextView = null
            } else {
                bodyTextView = (bodyTextView ?: SushiTextView(
                    context,
                    defStyleRes = R.style.TextAppearance_Sushi_Body
                )).apply {
                    text = value
                    layoutParams = itemLayoutParams
                }
            }
            relayout()
        }

    var labelText: String?
        get() = labelTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                labelTextView = null
            } else {
                labelTextView = (labelTextView ?: SushiTextView(
                    context,
                    defStyleRes = R.style.TextAppearance_Sushi_Label
                )).apply {
                    text = value
                    layoutParams = itemLayoutParams
                }
            }
            relayout()
        }

    init {
        orientation = LinearLayout.VERTICAL
        layoutTransition = LayoutTransition()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTextListing,
            defStyleAttr, defStyleRes
        ).let {

            headlineText = it.getString(R.styleable.SushiTextListing_headlineText)
            bodyText = it.getString(R.styleable.SushiTextListing_bodyText)
            labelText = it.getString(R.styleable.SushiTextListing_labelText)
            subtitleText = it.getString(R.styleable.SushiTextListing_subtitleText)
            initialized = true
            relayout()

            it.recycle()
        }
    }

    private fun relayout() {
        if (!initialized) return

        removeAllViews()
        labelTextView?.let { addView(it) }
        headlineTextView?.let { addView(it) }
        subtitleTextView?.let { addView(it) }
        bodyTextView?.let { addView(it) }
    }

}