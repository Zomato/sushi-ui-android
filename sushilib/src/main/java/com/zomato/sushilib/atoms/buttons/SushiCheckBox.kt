package com.zomato.sushilib.atoms.buttons

import android.content.Context
import android.support.annotation.ColorInt
import android.support.v7.widget.AppCompatCheckBox
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.widgets.CompoundButtonHelper

class SushiCheckBox @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.checkboxStyle
) : AppCompatCheckBox(ctx, attrs, defStyleAttr) {

    private val compoundButtonHelper = CompoundButtonHelper(this)

    init {
        context?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiCompoundButton
        )?.let {
            compoundButtonHelper.init(it)
            it.recycle()
        }
    }

    fun setCheckBoxColor(@ColorInt color: Int) {
        compoundButtonHelper.setControlColor(color)
    }
}