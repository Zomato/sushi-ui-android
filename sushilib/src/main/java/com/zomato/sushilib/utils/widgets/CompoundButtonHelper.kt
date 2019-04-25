package com.zomato.sushilib.utils.widgets

import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.widget.CompoundButton
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.theme.ResourceThemeResolver

class CompoundButtonHelper(private val compoundButton: CompoundButton) {

    @ColorInt
    private var color: Int =
        ResourceThemeResolver.getThemedColorFromAttr(compoundButton.context, android.R.attr.colorControlActivated)

    fun init(attrs: AttributeSet?) {
        compoundButton.context?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiCompoundButton
        )?.let {
            color = it.getColor(R.styleable.SushiCompoundButton_controlColor, color)
            applyCompundButtonColor()
            it.recycle()
        }
    }

    fun setControlColor(@ColorInt color: Int) {
        if (this.color == color) return
        this.color = color
        applyCompundButtonColor()
    }

    private fun applyCompundButtonColor() {
        compoundButton.buttonTintList = CompoundButtonUtils.getBackgroundTintList(compoundButton.context, color)
    }

}