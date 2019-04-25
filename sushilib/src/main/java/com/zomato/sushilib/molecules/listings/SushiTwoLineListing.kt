package com.zomato.sushilib.molecules.listings

import android.content.Context
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
open class SushiTwoLineListing @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = R.style.Theme_Sushi_Listing
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var initialized = false

    private var labelTextView: SushiTextView? = null
    private var headlineTextView: SushiTextView? = null
    private var bodyTextView: SushiTextView? = null

    var headingText: String?
        get() = headlineTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                headlineTextView = null
            } else {
                headlineTextView = (headlineTextView ?: SushiTextView(
                    context,
                    defStyleRes = R.style.Theme_Sushi_Listing_HeadLine
                )).apply {
                    text = value
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
                    defStyleRes = R.style.Theme_Sushi_Listing_Body
                )).apply {
                    text = value
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
                    defStyleRes = R.style.Theme_Sushi_Listing_Label
                )).apply {
                    text = value
                }
            }
            relayout()
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTwoLineListing,
            defStyleAttr, defStyleRes
        ).let {

            headingText = it.getString(R.styleable.SushiTwoLineListing_headlineText)
            bodyText = it.getString(R.styleable.SushiTwoLineListing_bodyText)
            labelText = it.getString(R.styleable.SushiTwoLineListing_labelText)

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
        bodyTextView?.let { addView (it) }
    }

}