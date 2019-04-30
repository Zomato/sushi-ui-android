package com.zomato.sushilib.molecules.listings

import android.content.Context
import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.imageviews.SushiCircleImageView
import com.zomato.sushilib.atoms.textviews.SushiIconTextView

/**
 * Created by prempal on 2019-04-30.
 */
open class SushiImageTextListing constructor(
    ctx: Context, attrs: AttributeSet? = null
) : LinearLayout(ctx, attrs) {

    private var initialized = false

    private var textListing = SushiTextListing(context, attrs)
    private var cornerIconTextView: SushiIconTextView? = null
    var imageView = SushiCircleImageView(context)

    var cornerIcon: String?
        get() = cornerIconTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                cornerIconTextView = null
            } else {
                cornerIconTextView = (cornerIconTextView ?: SushiIconTextView(context).apply { text = value })
            }
            relayout()
        }

    var iconTint: ColorStateList?
        get() = cornerIconTextView?.textColors
        set(value) {
            cornerIconTextView?.setTextColor(value)
        }

    init {
        orientation = LinearLayout.HORIZONTAL
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiImageTextListing,
            0, 0
        ).let {
            cornerIcon = it.getString(R.styleable.SushiImageTextListing_cornerIcon)
            iconTint = it.getColorStateList(R.styleable.SushiImageTextListing_iconTint)?.let { it }
                ?: ColorStateList.valueOf(
                    it.getColor(
                        R.styleable.SushiImageTextListing_iconTint,
                        ContextCompat.getColor(context, R.color.sushi_grey_300)
                    )
                )
            it.getDrawable(R.styleable.SushiImageTextListing_imageSrc)?.let {
                imageView.setImageDrawable(it)
            }
            it.recycle()
        }
        initialized = true
        relayout()
    }

    private fun relayout() {
        if (!initialized) return
        removeAllViews()
        val imageViewParams = LinearLayout.LayoutParams(
            resources.getDimensionPixelOffset(R.dimen.sushi_listing_image_size),
            resources.getDimensionPixelOffset(R.dimen.sushi_listing_image_size)
        ).apply {
            rightMargin = resources.getDimensionPixelOffset(R.dimen.sushi_spacing_base)
        }
        addView(imageView, imageViewParams)
        val textListingParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
        )
        addView(textListing, textListingParams)
        cornerIconTextView?.let {
            addView(it)
            it.setPadding(resources.getDimensionPixelOffset(R.dimen.sushi_spacing_micro), 0, 0, 0)
        }
    }

}