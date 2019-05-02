package com.zomato.sushilib.utils.widgets

import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.RestrictTo
import android.util.AttributeSet
import android.widget.CompoundButton
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.theme.ResourceThemeResolver

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class CompoundButtonHelper(private val compoundButton: CompoundButton) {

    @ColorInt
    private var color: Int =
        ResourceThemeResolver.getThemedColorFromAttr(compoundButton.context, android.R.attr.colorControlActivated)

    fun init(attrs: AttributeSet?, @AttrRes defStyleAttr: Int) {
        compoundButton.context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiCompoundButton,
            0, 0
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