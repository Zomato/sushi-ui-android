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


    private var mHeadlineTextView: SushiTextView? = null
    private var mBodyTextView: SushiTextView? = null

    var headingText: String?
        get() = mHeadlineTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                removeView(mHeadlineTextView)
                mHeadlineTextView = null
            } else {
                mHeadlineTextView = mHeadlineTextView ?: SushiTextView(
                    context,
                    defStyleRes = R.style.Theme_Sushi_Listing_HeadLine
                )
                if (indexOfChild(mHeadlineTextView) == -1) {
                    addView(mHeadlineTextView, 0)
                }
                mHeadlineTextView!!.text = value
            }
        }


    init {
        mHeadlineTextView = SushiTextView(
            context,
            defStyleRes = R.style.Theme_Sushi_Listing_HeadLine
        )
        mBodyTextView = SushiTextView(
            context,
            defStyleRes = R.style.Theme_Sushi_Listing_Body
        )
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTwoLineListing,
            defStyleAttr, defStyleRes
        ).let {
            mHeadlineTextView?.text = it.getString(R.styleable.SushiTwoLineListing_headlineText)
            mBodyTextView?.text = it.getString(R.styleable.SushiTwoLineListing_bodyText)
            it.recycle()
        }

        addView(mHeadlineTextView)
        addView(mBodyTextView)
    }

}