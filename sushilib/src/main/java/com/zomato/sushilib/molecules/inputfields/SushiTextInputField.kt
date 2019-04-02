package com.zomato.sushilib.molecules.inputfields

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.LayoutDirection
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import com.zomato.sushilib.R

/**
 * created by championswimmer on 02/04/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
class SushiTextInputField @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = android.support.design.R.attr.textInputStyle
) : TextInputLayout(context, attrs, defStyleAttr) {

    interface EdgeDrawableClickListener {
        fun onDrawableStartClicked()
        fun onDrawableEndClicked()
    }

    interface TextValidator {
        /**
         * A function called every time the text changes, which you can
         * hook into to generate error messages
         *
         * @param text The text currenly in the EditText
         * @return An error as {@link java.lang.String} if the text is valid or {@code null} if the text is valid
         */
        fun validateText (text: Editable?): String?
    }


    private var mEdgeDrawableClickListener: EdgeDrawableClickListener? = null
    private var mTextValidator: TextValidator? = null

    private var mEditText: TextInputEditText

    fun setEdgeDrawableClickListener(listener: EdgeDrawableClickListener?) {
        if (mEdgeDrawableClickListener == null && listener != null) {
            prepareOnTouchListener()
        }
        mEdgeDrawableClickListener = listener
    }

    fun setTextValidator(validator: TextValidator?) {
        if (mTextValidator == null && validator != null) {
            prepareOnTextChangedListener()
        }

        mTextValidator = validator
    }
    fun setTextValidator(validator: (text: Editable?) -> String?) {
        setTextValidator(object: TextValidator {
            override fun validateText(text: Editable?): String? = validator(text)
        })
    }


    init {
        // WARNING: Never change the theme of this context
        mEditText = TextInputEditText(context)

        context?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTextInputField,
            defStyleAttr,
            0
        )?.let {
            // First set non-RTL type drawables
            mEditText.setCompoundDrawablesWithIntrinsicBounds(
                it.getDrawable(R.styleable.SushiTextInputField_drawableLeft),
                null,
                it.getDrawable(R.styleable.SushiTextInputField_drawableRight),
                null
            )
            // Override with RTL-able drawables if avaialble
            mEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                it.getDrawable(R.styleable.SushiTextInputField_drawableStart),
                null,
                it.getDrawable(R.styleable.SushiTextInputField_drawableEnd),
                null
            )
            mEditText.compoundDrawablePadding = it.getDimensionPixelSize(
                R.styleable.SushiTextInputField_drawablePadding,
                0
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mEditText.compoundDrawableTintList = it.getColorStateList(
                    R.styleable.SushiTextInputField_drawableTint
                )
            }

            it.recycle()
        }
        addView(mEditText)
    }

    private fun prepareOnTextChangedListener() {
        mEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                error = mTextValidator?.validateText(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun prepareOnTouchListener() {
        mEditText.setOnTouchListener { v, event ->
            val drRight: Drawable? =
                editText?.compoundDrawablesRelative?.get(2)
                    ?: editText?.compoundDrawables?.get(2)

            val drLeft: Drawable? =
                editText?.compoundDrawablesRelative?.get(0)
                    ?: editText?.compoundDrawables?.get(0)

            if (event.action == MotionEvent.ACTION_UP) {

                if (drLeft != null) {
                    if (layoutDirection == LayoutDirection.LTR) {
                        if (event.rawX <= (editText!!.left + editText!!.paddingLeft + drLeft.bounds.width())) {
                            mEdgeDrawableClickListener?.onDrawableStartClicked()
                            return@setOnTouchListener true
                        }
                    } else {
                        if (event.rawX >= (editText!!.right - drLeft.bounds.width())) {
                            mEdgeDrawableClickListener?.onDrawableStartClicked()
                            return@setOnTouchListener true
                        }
                    }
                }

                if (drRight != null) {
                    if (layoutDirection == LayoutDirection.LTR) {
                        if (event.rawX >= (editText!!.right - drRight.bounds.width())) {
                            mEdgeDrawableClickListener?.onDrawableEndClicked()
                            return@setOnTouchListener true
                        }
                    } else {
                        if (event.rawX <= (editText!!.left + editText!!.paddingLeft + drRight.bounds.width())) {
                            mEdgeDrawableClickListener?.onDrawableEndClicked()
                            return@setOnTouchListener true
                        }
                    }

                }

            }
            // Unless one of the above cases hit, we do not consume this event
            return@setOnTouchListener false
        }
    }
}