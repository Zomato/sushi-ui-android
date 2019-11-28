package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatRadioButton
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