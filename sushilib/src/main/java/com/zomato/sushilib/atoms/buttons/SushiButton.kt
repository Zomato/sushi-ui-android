package com.zomato.sushilib.atoms.buttons

import android.content.Context
import android.support.annotation.IntDef
import android.support.annotation.StyleRes
import android.support.design.button.MaterialButton
import android.util.AttributeSet
import android.util.TypedValue
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemeWrappedContext

class SushiButton @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : MaterialButton(
    getThemeWrappedContext(ctx, defStyleRes),
    attrs,
    defStyleAttr
) {
    init {

        context?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiButton
        )?.let {

            val buttonSize = it.getInt(R.styleable.SushiButton_buttonSize, 0)
            setButtonSize(buttonSize)

            it.recycle()
        }
    }

    fun setButtonSize(@ButtonSize size: Int) {
        when (size) {
            SIZE_LARGE -> {
                iconSize = resources.getDimensionPixelSize(R.dimen.sushi_iconsize_500)
                minHeight = resources.getDimensionPixelSize(R.dimen.sushi_button_large_minheight)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.sushi_textsize_500)
                )
            }
            SIZE_MEDIUM -> {
                iconSize = resources.getDimensionPixelSize(R.dimen.sushi_iconsize_300)
                minHeight = resources.getDimensionPixelSize(R.dimen.sushi_button_medium_minheight)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.sushi_textsize_300)
                )
            }
            SIZE_SMALL -> {
                iconSize = resources.getDimensionPixelSize(R.dimen.sushi_iconsize_200)
                minHeight = resources.getDimensionPixelSize(R.dimen.sushi_button_small_minheight)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.sushi_textsize_200)
                )
            }
        }
    }


    companion object {
        const val SIZE_LARGE = 0
        const val SIZE_MEDIUM = 1
        const val SIZE_SMALL = 2
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(SIZE_LARGE, SIZE_MEDIUM, SIZE_SMALL)
    annotation class ButtonSize
}