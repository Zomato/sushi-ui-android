package com.zomato.sushilib.molecules.inputfields

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.LayoutDirection
import android.util.TypedValue
import android.view.MotionEvent
import android.widget.EditText
import androidx.annotation.AttrRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.FontWeight
import com.zomato.sushilib.utils.text.TextFormatUtils
import com.zomato.sushilib.utils.widgets.TextViewUtils.applyDrawables


/**
 * created by championswimmer on 02/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
open class SushiTextInputField @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = com.google.android.material.R.attr.textInputStyle
) : TextInputLayout(ctx, attrs, defStyleAttr) {

    var editText: TextInputEditText = TextInputEditText(context)

    @get:FontWeight
    @setparam:FontWeight
    var textFontWeight: Int = FontWeight.REGULAR
        set(value) {
            field = value
            editText.setTextAppearance(
                context,
                TextFormatUtils.textFontWeightToTextAppearance(value)
            )
        }

    private var edgeDrawableClickListener: EdgeDrawableClickListener? = null
    private var textValidator: TextValidator? = null

    init {
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
            val type = it.getInt(R.styleable.SushiTextInputField_editTextType, -1)
            setEditTextType(type)
            editText.applyDrawables(
                attrs, defStyleAttr,
                ContextCompat.getColor(context, R.color.sushi_grey_400) ?: Color.GRAY,
                editText.textSize.toInt()
            )

            it.recycle()
        }
        this.addView(editText)
    }

    private fun setEditTextType(type: Int) {
        resolveAndSetType(editText, type)
    }

    private fun resolveAndSetType(editText: TextInputEditText, type: Int) {
        if (type == -1) return
        editText.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            editText.context.resources.getDimensionPixelOffset(resolveZTextType(type)).toFloat()
        )
        val textFontWeight = (type / 10 + 3) * 100
        this.textFontWeight = textFontWeight
    }

    @DimenRes
    private fun resolveZTextType(type: Int): Int {
        return when (type % 10) {
            0 -> R.dimen.sushi_textsize_050
            1 -> R.dimen.sushi_textsize_100
            2 -> R.dimen.sushi_textsize_200
            3 -> R.dimen.sushi_textsize_300
            4 -> R.dimen.sushi_textsize_400
            5 -> R.dimen.sushi_textsize_500
            6 -> R.dimen.sushi_textsize_600
            7 -> R.dimen.sushi_textsize_700
            8 -> R.dimen.sushi_textsize_800
            9 -> R.dimen.sushi_textsize_900
            else -> R.dimen.sushi_textsize_500
        }
    }

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

}