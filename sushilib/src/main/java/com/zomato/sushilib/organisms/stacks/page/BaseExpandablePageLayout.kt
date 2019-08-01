package com.zomato.sushilib.organisms.stacks.page

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Outline
import android.graphics.Rect
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import com.zomato.sushilib.organisms.stacks.AnimationConstants
import com.zomato.sushilib.organisms.stacks.AnimationConstants.DEFAULT_ANIM_DURATION

/**
 * Animating change in dimensions by changing the actual width and height is expensive.
 * This layout animates change in dimensions by clipping visible bounds instead.
 */
abstract class BaseExpandablePageLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    /** The visible portion of this layout. */
    val clippedDimens: Rect
        get() = clipBounds

    private var dimensionAnimator: ValueAnimator = ObjectAnimator()
    private var isFullyVisible: Boolean = false

    var animationDurationMillis = DEFAULT_ANIM_DURATION

    init {
        clipBounds = Rect()

        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRect(0, 0, clipBounds.width(), clipBounds.height())
                outline.alpha = clipBounds.height().toFloat() / height
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (isFullyVisible) {
            setClippedDimensions(w, h)
        }
    }

    fun animateDimensions(toWidth: Int, toHeight: Int, expand: Boolean) {
        dimensionAnimator.cancel()

        dimensionAnimator = ObjectAnimator.ofFloat(0F, 1F).apply {
            duration = animationDurationMillis
            interpolator = if (expand) {
                AnimationConstants.DEFAULT_EASE_INTERPOLATOR
            } else {
                AnimationConstants.DEFAULT_EASE_INTERPOLATOR
            }

            val fromWidth = clipBounds.width()
            val fromHeight = clipBounds.height()

            addUpdateListener {
                val scale = it.animatedValue as Float
                val newWidth = ((toWidth - fromWidth) * scale + fromWidth).toInt()
                val newHeight = ((toHeight - fromHeight) * scale + fromHeight).toInt()
                setClippedDimensions(newWidth, newHeight)
            }
        }
        dimensionAnimator.start()
    }

    fun setClippedDimensions(newClippedWidth: Int, newClippedHeight: Int) {
        isFullyVisible =
            newClippedWidth > 0 && newClippedHeight > 0 && newClippedWidth == width && newClippedHeight == height
        clipBounds = Rect(0, 0, newClippedWidth, newClippedHeight)
        invalidateOutline()
    }

    /** Immediately reset the clipping so that this layout is fully visible. */
    fun resetClipping() {
        setClippedDimensions(width, height)
    }
}
