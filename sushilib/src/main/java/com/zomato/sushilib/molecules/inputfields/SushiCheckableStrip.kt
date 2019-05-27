package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.StyleRes
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.Checkable
import android.widget.CompoundButton
import android.widget.LinearLayout
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
    @StyleRes defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr), Checkable {

    @ColorInt
    private var color: Int =
        ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.colorControlActivated)
    @ColorInt
    private var defaultColor: Int =
        ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.textColorPrimary)

    private var isChecked = false
    @CheckableSelectorType
    private var selectorType = CheckableSelectorType.RADIO_BUTTON
    private var compoundButton: CompoundButton? = null
    private var secondaryTextView: SushiTextView? = null
    private var onCheckedChangeListener: OnCheckedChangeListener? = null
    private var checkAllowedListener: SushiCheckBox.CheckAllowedListener? = null

    var primaryText: String?
        get() = compoundButton?.text?.toString()
        set(value) {
            compoundButton?.text = value
        }

    var secondaryText: String?
        get() = secondaryTextView?.text?.toString()
        set(value) {
            secondaryTextView = (secondaryTextView ?: SushiTextView(context)).also {
                addView(it)
            }.apply { text = value }
        }

    constructor(ctx: Context, @CheckableSelectorType selectorType: Int) : this(ctx) {
        this.selectorType = selectorType
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
                it.getInt(R.styleable.SushiCheckableStrip_selectorType, selectorType)
            color = it.getColor(R.styleable.SushiCheckableStrip_controlColor, color)

            compoundButton = when (selectorType) {
                CheckableSelectorType.RADIO_BUTTON -> SushiRadioButton(context)
                CheckableSelectorType.CHECKBOX -> SushiCheckBox(context)
                else -> null
            }
            compoundButton?.ellipsize = TextUtils.TruncateAt.END
            addView(compoundButton, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f))

            primaryText = it.getString(R.styleable.SushiCheckableStrip_primaryText)
            secondaryText = it.getString(R.styleable.SushiCheckableStrip_secondaryText)

            setChecked(it.getBoolean(R.styleable.SushiCheckableStrip_checked, false))

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

    fun setCheckAllowedListener(listener: SushiCheckBox.CheckAllowedListener) {
        checkAllowedListener = listener
        (compoundButton as? SushiCheckBox)?.setCheckAllowedListener(listener)
    }

    fun setCheckAllowedListener(listener: (isChecked: Boolean) -> Boolean) {
        setCheckAllowedListener(object : SushiCheckBox.CheckAllowedListener {
            override fun allowCheck(isChecked: Boolean): Boolean = listener(isChecked)
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
        if (isChecked != checked) {
            isChecked = checked
            compoundButton?.isChecked = checked
            setColors()
            onCheckedChangeListener?.onCheckedChanged(this, checked)
        }
    }

    private fun setColors() {
        if (isChecked) {
            secondaryTextView?.setTextColor(color)
            compoundButton?.setTextColor(color)
        } else {
            secondaryTextView?.setTextColor(defaultColor)
            compoundButton?.setTextColor(defaultColor)
        }
        when (selectorType) {
            CheckableSelectorType.RADIO_BUTTON -> (compoundButton as? SushiRadioButton)?.setControlColor(color)
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