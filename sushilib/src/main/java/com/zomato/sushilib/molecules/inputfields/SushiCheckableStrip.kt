package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Checkable
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.CheckableSelectorType
import com.zomato.sushilib.atoms.textviews.SushiTextView
import com.zomato.sushilib.utils.theme.ResourceThemeResolver

/**
 * Created by prempal on 2019-05-21.
 */
open class SushiCheckableStrip @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    @CheckableSelectorType private var selectorType: Int = CheckableSelectorType.RADIO,
    private var reverseLayout: Boolean = false
) : LinearLayout(ctx, attrs, defStyleAttr), Checkable {

    @ColorInt
    private var color: Int =
        ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.colorControlActivated)
    @ColorInt
    private var defaultColor: Int =
        ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.textColorPrimary)

    private var isChecked = false
    protected var compoundButton: CompoundButton? = null
    protected var primaryTextView: SushiTextView? = null
    protected var secondaryTextView: SushiTextView? = null
    private var onCheckedChangeListener: OnCheckedChangeListener? = null
    private var checkChangeAllowedListener: SushiCheckBox.CheckChangeAllowedListener? = null

    var primaryText: String?
        get() = if (reverseLayout)
            primaryTextView?.text?.toString()
        else
            compoundButton?.text?.toString()
        set(value) {
            // if (reverseLayout)
            //     primaryTextView = (primaryTextView ?: SushiTextView(context)).apply { text = value }
            // else
            //     compoundButton?.text = value
            primaryTextView = (primaryTextView ?: SushiTextView(context)).apply { text = value }
        }

    var secondaryText: String?
        get() = secondaryTextView?.text?.toString()
        set(value) {
            secondaryTextView = (secondaryTextView ?: SushiTextView(context).also {
                // if (!reverseLayout)
                //     addView(it)
            }).apply { text = value }
        }

    init {
        orientation = HORIZONTAL
        isClickable = true
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiCheckableStrip,
            defStyleAttr, defStyleRes
        ).let {

            selectorType =
                it.getInt(R.styleable.SushiCheckableStrip_selector, selectorType)
            color = it.getColor(R.styleable.SushiCheckableStrip_controlColor, color)

            compoundButton = when (selectorType) {
                CheckableSelectorType.RADIO -> SushiRadioButton(context)
                CheckableSelectorType.CHECKBOX -> SushiCheckBox(context)
                else -> null
            }
            primaryText = it.getString(R.styleable.SushiCheckableStrip_primaryText)
            secondaryText = it.getString(R.styleable.SushiCheckableStrip_secondaryText)
            compoundButton?.ellipsize = TextUtils.TruncateAt.END
            if (reverseLayout) {
                addView(primaryTextView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f))
                addView(secondaryTextView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
                addView(compoundButton, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
            } else {
                addView(compoundButton, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
                addView(LinearLayout(context).apply {
                    orientation = VERTICAL
                    addView(primaryTextView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
                    addView(secondaryTextView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
                }, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f).apply {
                    gravity = Gravity.CENTER_VERTICAL
                })
            }

            setChecked(it.getBoolean(R.styleable.SushiCheckableStrip_android_checked, false))
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

            it.recycle()
        }

        setColors()

        compoundButton?.setOnCheckedChangeListener { buttonView, isChecked ->
            setChecked(isChecked)
        }

        setOnClickListener {
            compoundButton?.performClick()
        }
    }

    /**
     * Sets the listener that checks whether it is allowed for the checkable strip to change checked state.
     * Only works for [CheckableSelectorType.CHECKBOX]
     *
     * @param listener The listener.
     */
    fun setCheckChangeAllowedListener(listener: SushiCheckBox.CheckChangeAllowedListener?) {
        checkChangeAllowedListener = listener
        (compoundButton as? SushiCheckBox)?.setCheckChangeAllowedListener(listener)
    }

    /**
     * Sets the lambda to be called that checks whether it is allowed for the checkable strip to change checked state.
     * Only works for [CheckableSelectorType.CHECKBOX]
     *
     * @param listener The lambda.
     */
    fun setCheckChangeAllowedListener(listener: (isChecked: Boolean) -> Boolean) {
        setCheckChangeAllowedListener(object : SushiCheckBox.CheckChangeAllowedListener {
            override fun isCheckChangeAllowed(isChecked: Boolean): Boolean = listener(isChecked)
        })
    }

    /**
     * Sets the listener to be called when the checked state changes.
     *
     * @param listener The listener.
     */
    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        onCheckedChangeListener = listener
    }

    /**
     * Sets the lambda to be called when the checked state changes.
     *
     * @param listener The lambda.
     */
    fun setOnCheckedChangeListener(listener: (view: SushiCheckableStrip, isChecked: Boolean) -> Unit) {
        setOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChanged(view: SushiCheckableStrip, isChecked: Boolean) = listener(view, isChecked)
        })
    }

    fun setControlColor(@ColorInt color: Int) {
        this.color = color
        setColors()
    }

    override fun isChecked() = isChecked

    override fun toggle() {
        setChecked(!isChecked)
    }

    override fun setChecked(checked: Boolean) {
        secondaryTextView?.visibility = if (checked) View.VISIBLE else View.GONE
        if (isChecked != checked) {
            isChecked = checked
            compoundButton?.isChecked = checked
            setColors()
            onCheckedChangeListener?.onCheckedChanged(this, checked)
        }
    }

    private fun setColors() {
        if (isChecked) {
            // primaryTextView?.setTextColor(color)
            // secondaryTextView?.setTextColor(color)
            compoundButton?.setTextColor(color)
        } else {
            // primaryTextView?.setTextColor(defaultColor)
            // secondaryTextView?.setTextColor(defaultColor)
            compoundButton?.setTextColor(defaultColor)
        }
        when (selectorType) {
            CheckableSelectorType.RADIO -> (compoundButton as? SushiRadioButton)?.setControlColor(color)
            CheckableSelectorType.CHECKBOX -> (compoundButton as? SushiCheckBox)?.setControlColor(color)
        }
    }

    /**
     * Interface definition for a callback to be invoked when the checked state of SushiCheckableStrip has changed.
     */
    interface OnCheckedChangeListener {
        /**
         * Called when the checked state of a checkable strip has changed.
         *
         * @param view The view whose state has changed.
         * @param isChecked  The new checked state of view.
         */
        fun onCheckedChanged(view: SushiCheckableStrip, isChecked: Boolean)
    }

}