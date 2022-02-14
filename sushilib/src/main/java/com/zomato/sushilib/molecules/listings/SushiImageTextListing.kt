package com.zomato.sushilib.molecules.listings

import android.content.Context
import android.content.res.ColorStateList
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.imageviews.SushiCircleImageView
import com.zomato.sushilib.atoms.textviews.SushiIconTextView

/**
 * Created by prempal on 2019-04-30.
 */
open class SushiImageTextListing @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr, defStyleRes) {

    private var initialized = false

    protected var cornerIconTextView: SushiIconTextView? = null
    protected var textListing = SushiTextListing(context, attrs)
    protected var imageView = SushiCircleImageView(context)

    var imagePadding
        get() = imageView.paddingLeft
        set(value) {
            imageView.setPadding(value, value, value, value)
        }

    var cornerIconChar: String?
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
            defStyleAttr, defStyleRes
        ).let {
            cornerIconChar = it.getString(R.styleable.SushiImageTextListing_cornerIconChar)
            iconTint = it.getColorStateList(R.styleable.SushiImageTextListing_iconTint)?.let { it }
                ?: ColorStateList.valueOf(
                    it.getColor(
                        R.styleable.SushiImageTextListing_iconTint,
                        ContextCompat.getColor(context, R.color.sushi_grey_300)
                    )
                )
            imagePadding = it.getDimensionPixelOffset(R.styleable.SushiImageTextListing_imagePadding, 0)
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