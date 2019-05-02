package com.zomato.sushilib.atoms.buttons

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.StyleRes
import android.support.design.button.MaterialButton
import android.util.AttributeSet
import android.util.TypedValue
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.ButtonDimension
import com.zomato.sushilib.annotations.ButtonType
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemeWrappedContext
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemedColorFromAttr
import com.zomato.sushilib.utils.widgets.ButtonStyleUtils

open class SushiButton @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.buttonStyle, @StyleRes defStyleRes: Int = 0
) : MaterialButton(
    getThemeWrappedContext(ctx, defStyleRes),
    attrs,
    defStyleAttr
) {

    @ButtonDimension
    private var buttonDimension: Int = ButtonDimension.LARGE
    @ButtonType
    private var buttonType: Int = ButtonType.SOLID
    @ColorInt
    private var buttonColor: Int = getThemedColorFromAttr(context, R.attr.colorAccent)
    @ColorInt
    private var customStrokeColor: Int = buttonColor

    init {

        context?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiButton
        )?.let {

            buttonDimension = it.getInt(R.styleable.SushiButton_buttonDimension, ButtonDimension.LARGE)
            buttonType = it.getInt(R.styleable.SushiButton_buttonType, ButtonType.SOLID)
            buttonColor = it.getColor(R.styleable.SushiButton_buttonColor, buttonColor)
            customStrokeColor = buttonColor

            reapplySizes()
            reapplyStyles()

            // If user has set a ColorStateList or Color we take that
            it.getColorStateList(R.styleable.SushiButton_strokeColor)?.let { strokeColorStateList ->
                strokeColor = strokeColorStateList
            } ?: setStrokeColor(it.getColor(R.styleable.SushiButton_strokeColor, buttonColor))

            it.recycle()
        }
    }

    @ColorInt
    fun getButtonColor(): Int {
        return buttonColor
    }

    fun setButtonColor(@ColorInt color: Int) {
        if (color == buttonColor) return
        buttonColor = color
        reapplyStyles()
    }

    @ButtonType
    fun getButtonType(): Int {
        return buttonType
    }

    fun setButtonType(@ButtonType style: Int) {
        if (style == buttonType) return
        buttonType = style
        reapplyStyles()
    }

    private fun reapplyStyles() {
        ButtonStyleUtils.apply {
            applyStrokeWidth()
            applyIconPadding()
            applyRippleColor()
            applyBackgroundTintList()
            applyIconAndTextColor()
            applyStrokeColor(customStrokeColor)
        }
    }

    @ButtonDimension
    fun getButtonDimension(): Int {
        return buttonDimension
    }

    fun setButtonDimension(@ButtonDimension size: Int) {
        if (size == buttonDimension) return
        buttonDimension = size
        reapplySizes()
    }

    private fun setStrokeColor(@ColorInt color: Int) {
        customStrokeColor = color
        ButtonStyleUtils.apply {
            applyStrokeColor(customStrokeColor)
        }
    }

    private fun reapplySizes() {
        when (buttonDimension) {
            ButtonDimension.LARGE -> {
                iconSize = resources.getDimensionPixelSize(R.dimen.sushi_iconsize_500)
                minHeight = resources.getDimensionPixelSize(R.dimen.sushi_button_large_minheight)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.sushi_textsize_500)
                )
            }
            ButtonDimension.MEDIUM -> {
                iconSize = resources.getDimensionPixelSize(R.dimen.sushi_iconsize_300)
                minHeight = resources.getDimensionPixelSize(R.dimen.sushi_button_medium_minheight)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.sushi_textsize_300)
                )
            }
            ButtonDimension.SMALL -> {
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