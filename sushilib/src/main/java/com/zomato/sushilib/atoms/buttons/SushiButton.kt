package com.zomato.sushilib.atoms.buttons

import android.content.Context
import android.support.annotation.IntDef
import android.support.annotation.StyleRes
import android.support.design.button.MaterialButton
import android.util.AttributeSet
import android.util.TypedValue
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.ButtonSize
import com.zomato.sushilib.annotations.ButtonStyle
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
            val buttonStyle: Int = it.getInt(R.styleable.SushiButton_buttonStyle, 0)

            setButtonSize(buttonSize)
            setButtonStyle(buttonStyle)
            it.recycle()
        }
    }

    fun setButtonStyle(@ButtonStyle style: Int) {

    }

    fun setButtonSize(@ButtonSize size: Int) {
        when (size) {
            ButtonSize.LARGE -> {
                iconSize = resources.getDimensionPixelSize(R.dimen.sushi_iconsize_500)
                minHeight = resources.getDimensionPixelSize(R.dimen.sushi_button_large_minheight)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.sushi_textsize_500)
                )
            }
            ButtonSize.MEDIUM -> {
                iconSize = resources.getDimensionPixelSize(R.dimen.sushi_iconsize_300)
                minHeight = resources.getDimensionPixelSize(R.dimen.sushi_button_medium_minheight)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.sushi_textsize_300)
                )
            }
            ButtonSize.SMALL -> {
                iconSize = resources.getDimensionPixelSize(R.dimen.sushi_iconsize_200)
                minHeight = resources.getDimensionPixelSize(R.dimen.sushi_button_small_minheight)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.sushi_textsize_200)
                )
            }
        }
    }

}