package com.zomato.sushilib.molecules.inputfields

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.LayoutDirection
import android.view.MotionEvent
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.widgets.TextViewUtils.applyDrawables


/**
 * created by championswimmer on 02/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
open class SushiTextInputField @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = com.google.android.material.R.attr.textInputStyle
) : TextInputLayout(ctx, attrs, defStyleAttr) {

    interface EdgeDrawableClickListener {
        /**
         * method called when drawableLeft (or drawableStart
         * if RTL supported) is clicked
         */
        fun onDrawableStartClicked()

        /**
         * method called when drawableRight (or drawableEnd
         * if RTL supported) is clicked
         */
        fun onDrawableEndClicked()
    }

    interface TextValidator {
        /**
         * A function called every time the text changes, which you can
         * hook into to generate error messages
         *
         * @param text The text currenly in the EditText
         * @return An error as [String] if the text is invalid
         * or [null] if the text is valid
         */
        fun validateText(text: Editable?): String?
    }


    private var edgeDrawableClickListener: EdgeDrawableClickListener? = null
    private var textValidator: TextValidator? = null

    var editText: TextInputEditText

    /**
     * Set an [EdgeDrawableClickListener]
     */
    fun setEdgeDrawableClickListener(listener: EdgeDrawableClickListener?) {
        if (edgeDrawableClickListener == null && listener != null) {
            prepareOnTouchListener()
        }
        edgeDrawableClickListener = listener
    }

    fun setTextValidator(validator: TextValidator?) {
        if (textValidator == null && validator != null) {
            prepareOnTextChangedListener()
        }

        textValidator = validator
    }

    fun setTextValidator(validator: (text: Editable?) -> String?) {
        setTextValidator(object : TextValidator {
            override fun validateText(text: Editable?): String? = validator(text)
        })
    }


    init {
        // WARNING: Never change the theme of this context
        editText = TextInputEditText(context)

        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTextInputField,
            defStyleAttr,
            0
        )?.let {
            val attrInputType = it.getInt(
                R.styleable.SushiTextInputField_android_inputType,
                -1
            )
            if (attrInputType != -1) {
                editText.inputType = attrInputType
            }
            editText.applyDrawables(
                attrs, defStyleAttr,
                ContextCompat.getColor(context, R.color.sushi_grey_400) ?: Color.GRAY,
                editText.textSize.toInt()
            )

            it.recycle()
        }
        this.addView(editText)
    }

    private fun prepareOnTextChangedListener() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                error = textValidator?.validateText(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun prepareOnTouchListener() {
        editText.setOnTouchListener { v, event ->
            val drRight: Drawable? =
                editText.compoundDrawablesRelative.get(2)
                    ?: editText.compoundDrawables.get(2)

            val drLeft: Drawable? =
                editText.compoundDrawablesRelative.get(0)
                    ?: editText.compoundDrawables.get(0)

            if (event.action == MotionEvent.ACTION_UP) {

                if (drLeft != null) {
                    if (layoutDirection == LayoutDirection.LTR) {
                        if (event.rawX <= (editText.left + editText.paddingLeft + drLeft.bounds.width())) {
                            edgeDrawableClickListener?.onDrawableStartClicked()
                            return@setOnTouchListener true
                        }
                    } else {
                        if (event.rawX >= (editText.right - drLeft.bounds.width())) {
                            edgeDrawableClickListener?.onDrawableStartClicked()
                            return@setOnTouchListener true
                        }
                    }
                }

                if (drRight != null) {
                    if (layoutDirection == LayoutDirection.LTR) {
                        if (event.rawX >= (editText.right - drRight.bounds.width())) {
                            edgeDrawableClickListener?.onDrawableEndClicked()
                            return@setOnTouchListener true
                        }
                    } else {
                        if (event.rawX <= (editText.left + editText.paddingLeft + drRight.bounds.width())) {
                            edgeDrawableClickListener?.onDrawableEndClicked()
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