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
    private var checkChangeAllowedListener: CheckChangeAllowedListener? = null

    init {
        compoundButtonHelper.init(attrs, defStyleAttr)
    }

    fun setControlColor(@ColorInt color: Int) {
        compoundButtonHelper.setControlColor(color)
    }

    /**
     * Sets the listener that checks whether it is allowed for the checkbox to change checked state.
     *
     * @param listener The listener.
     */
    fun setCheckChangeAllowedListener(listener: CheckChangeAllowedListener?) {
        checkChangeAllowedListener = listener
    }

    /**
     * Sets the lambda to be called that checks whether it is allowed for the checkbox to change checked state.
     *
     * @param listener The lambda.
     */
    fun setCheckChangeAllowedListener(listener: (isChecked: Boolean) -> Boolean) {
        setCheckChangeAllowedListener(object : CheckChangeAllowedListener {
            override fun isCheckChangeAllowed(isChecked: Boolean): Boolean = listener(isChecked)
        })
    }

    override fun performClick(): Boolean {
        return if (checkChangeAllowedListener == null || checkChangeAllowedListener?.isCheckChangeAllowed(isChecked) == true) {
            super.performClick()
        } else {
            true
        }
    }

    /**
     * Interface definition for a callback that checks whether it is allowed for the checkbox to change checked state.
     */
    interface CheckChangeAllowedListener {
        /**
         * Checks whether it is allowed for the checkbox to change checked state.
         *
         * @param isChecked Current checked state of the checkbox
         * @return boolean whether it is allowed for the checkbox to change checked state
         */
        fun isCheckChangeAllowed(isChecked: Boolean): Boolean
    }
}