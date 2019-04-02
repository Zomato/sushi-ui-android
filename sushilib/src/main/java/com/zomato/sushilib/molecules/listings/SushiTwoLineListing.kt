package com.zomato.sushilib.molecules.listings

import android.animation.LayoutTransition
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiTextView

/**
 * created by championswimmer on 29/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 *
 * @constructor overloads all Java ones
 *
 * NOTE: @JvmOverloads is fine here as the default
 * constructors do not have any defStyleRes or defStyleAttr
 */
class SushiTwoLineListing @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, R.style.Theme_Sushi_Listing) {


    private var mHeadingTextView: SushiTextView? = null
    private var mBodyTextView: SushiTextView? = null

    var headingText: String?
        get() = mHeadingTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                removeView(mHeadingTextView)
                mHeadingTextView = null
            } else {
                mHeadingTextView = mHeadingTextView ?: SushiTextView(
                    context,
                    defStyleRes = R.style.Theme_Sushi_Listing_HeadLine
                )
                if (indexOfChild(mHeadingTextView) == -1) {
                    addView(mHeadingTextView, 0)
                }
                mHeadingTextView!!.text = value
            }
        }


    init {
        mHeadingTextView = SushiTextView(
            context,
            defStyleRes = R.style.Theme_Sushi_Listing_HeadLine
        )
        mBodyTextView = SushiTextView(
            context,
            defStyleRes = R.style.Theme_Sushi_Listing_Body
        )
        addView(mHeadingTextView)
        addView(mBodyTextView)
    }


}