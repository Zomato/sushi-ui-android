package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.v7.widget.AppCompatCheckBox
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.widgets.CompoundButtonHelper

open class SushiCheckBox @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.checkboxStyle
) : AppCompatCheckBox(ctx, attrs, defStyleAttr) {

    private val compoundButtonHelper = CompoundButtonHelper(this)
    private var checkAllowedListener: CheckAllowedListener? = null

    init {
        compoundButtonHelper.init(attrs, defStyleAttr)
    }

    fun setControlColor(@ColorInt color: Int) {
        compoundButtonHelper.setControlColor(color)
    }

    fun setCheckAllowedListener(listener: CheckAllowedListener) {
        checkAllowedListener = listener
    }

    fun setCheckAllowedListener(listener: (isChecked: Boolean) -> Boolean) {
        setCheckAllowedListener(object : CheckAllowedListener {
            override fun allowCheck(isChecked: Boolean): Boolean = listener(isChecked)
        })
    }

    override fun performClick(): Boolean {
        return if (checkAllowedListener == null || checkAllowedListener?.allowCheck(isChecked) == true) {
            super.performClick()
        } else {
            true
        }
    }

    /**
     * Interface definition for a callback that checks whether it is allowed for the checkbox to change checked states.
     */
    interface CheckAllowedListener {
        /**
         * Checks whether it is allowed for the checkbox to change checked states.
         *
         * @param isChecked Checked state of the checkbox
         * @return boolean whether it is allowed for the checkbox to change checked states
         */
        fun allowCheck(isChecked: Boolean): Boolean
    }
}