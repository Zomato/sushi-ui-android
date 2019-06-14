package com.zomato.sushilib.organisms.ratings

import android.content.Context
import android.content.res.ColorStateList
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.IntRange
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.TagSize
import com.zomato.sushilib.annotations.TagType
import com.zomato.sushilib.molecules.tags.SushiTag
import com.zomato.sushilib.utils.widgets.TagStyleUtils

/**
 * created by championswimmer on 26/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class SushiRatingBar @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr, defStyleRes) {

    private var ratingTags: ArrayList<SushiTag> = ArrayList(5)
    @TagType
    private var internalTagType: Int = TagType.CAPSULE
    private var initialized = false
    private var ratingColorStateList = ContextCompat.getColorStateList(
        context,
        R.color.sushi_rating_color_selector
    )
    private var onRatingChangeListener: OnRatingChangeListener? = null

    @get:IntRange(from = 0, to = 5)
    @setparam:IntRange(from = 0, to = 5)
    var rating: Int = 0
        set(value) {
            field = value
            reapplyRatingToAllTags()
        }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SushiRatingBar, defStyleAttr, defStyleRes)
        val tagSize = ta.getInt(R.styleable.SushiRatingBar_tagSize, TagSize.LARGE)
        val attrTagType = ta.getInt(R.styleable.SushiRatingBar_tagType, TagType.CAPSULE)
        rating = ta.getFloat(R.styleable.SushiRatingBar_rating, 0f).toInt()

        internalTagType = if (attrTagType == TagType.CAPSULE || attrTagType == TagType.CAPSULE_OUTLINE) {
            TagType.CAPSULE
        } else {
            TagType.ROUNDED
        }
        isClickable = ta.getBoolean(R.styleable.SushiRatingBar_android_clickable, true)
        ta.recycle()

        val tagMargin = resources.getDimensionPixelSize(R.dimen.sushi_spacing_nano)
        val tagMinWidth = resources.getDimensionPixelSize(
            TagStyleUtils.getMinWidthResIdForRatingTagBySize(tagSize)
        )

        for (i in 1..5) {
            ratingTags.add(
                SushiTag(context, attrs, R.attr.ratingTagStyle).apply {
                    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                        setMargins(tagMargin, tagMargin, tagMargin, tagMargin)
                    }
                    minWidth = tagMinWidth
                    text = "$i"
                    setOnClickListener {
                        // If someone returns false, do not change rating
                        if (onRatingChangeListener?.onRatingChanged(i) != false)
                            rating = i
                    }
                    this.isClickable = this@SushiRatingBar.isClickable
                }
            )
        }

        ratingTags.forEach { tag ->
            addView(tag)
        }
        initialized = true
        reapplyRatingToAllTags()
    }

    /**
     * Sets the listener to be called when the rating changes.
     *
     * @param listener The listener.
     */
    fun setOnRatingChangeListener(listener: OnRatingChangeListener?) {
        onRatingChangeListener = listener
    }


    /**
     * Sets the lambda to be called when the rating changes.
     *
     * @param listener The lambda.
     */
    fun setOnRatingChangeListener(listener: (rating: Int) -> Boolean) {
        setOnRatingChangeListener(object : OnRatingChangeListener {
            override fun onRatingChanged(rating: Int) = listener(rating)
        })
    }

    override fun setClickable(clickable: Boolean) {
        ratingTags.forEach { it.isClickable = clickable }
        super.setClickable(clickable)
    }

    private fun reapplyRatingToAllTags() {
        if (!initialized) return

        for (i in 1..5) {
            if (i > rating) {
                ratingTags[i - 1].apply {
                    tagColor = getRatingColor(0)
                    tagType = when (internalTagType) {
                        TagType.ROUNDED -> TagType.ROUNDED_OUTLINE
                        TagType.CAPSULE -> TagType.CAPSULE_OUTLINE; else -> TagType.CAPSULE_OUTLINE
                    }
                    setTextColor(ColorStateList.valueOf(tagColor))
                }
            }
            if (i <= rating) {
                ratingTags[i - 1].apply {
                    tagColor = getRatingColor(rating)
                    tagType = when (internalTagType) {
                        TagType.ROUNDED -> TagType.ROUNDED
                        TagType.CAPSULE -> TagType.CAPSULE; else -> TagType.CAPSULE
                    }

                    if (i < rating) {
                        setTextColor(ColorStateList.valueOf(tagColor))
                    }

                }
            }
        }
    }

    @ColorInt
    private fun getRatingColor(r: Int): Int {
        return ratingColorStateList?.getColorForState(
            when (r) {
                1 -> intArrayOf(R.attr.state_rating_1)
                2 -> intArrayOf(R.attr.state_rating_2)
                3 -> intArrayOf(R.attr.state_rating_3)
                4 -> intArrayOf(R.attr.state_rating_4)
                5 -> intArrayOf(R.attr.state_rating_5)
                else -> intArrayOf()
            },
            0
        ) ?: 0
    }

    /**
     * Interface definition for a callback to be invoked when the rating has been changed.
     */
    interface OnRatingChangeListener {
        /**
         * Called when the rating has been changed.
         *
         * @param rating The current rating.
         * @return false if you want to prevent the rating actually being changed
         */
        fun onRatingChanged(rating: Int): Boolean
    }
}
