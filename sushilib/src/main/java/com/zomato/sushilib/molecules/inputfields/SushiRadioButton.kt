package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.v7.widget.AppCompatRadioButton
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.widgets.CompoundButtonHelper

open class SushiRadioButton @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.radioButtonStyle
) : AppCompatRadioButton(ctx, attrs, defStyleAttr) {

    private val compoundButtonHelper = CompoundButtonHelper(this)

    init {
        compoundButtonHelper.init(attrs, defStyleAttr)
    }

    fun setControlColor(@ColorInt color: Int) {
        compoundButtonHelper.setControlColor(color)
    }
}