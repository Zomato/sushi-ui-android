package com.zomato.sushilib.molecules.listings

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.StyleRes
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiTextView


/**
 * Created by prempal on 2019-04-29.
 */
open class SushiLabeledStrip @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var initialized = false

    private var labelTextView: SushiTextView? = null
    private var textView: SushiTextView? = null

    var text: String?
        get() = textView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                textView = null
            } else {
                textView = (textView ?: SushiTextView(context).apply { text = value })
            }
            relayout()
        }

    var labelText: String?
        get() = labelTextView?.text?.toString()
        set(value) {
            if (TextUtils.isEmpty(value)) {
                labelTextView = null
            } else {
                labelTextView = (labelTextView ?: SushiTextView(context).apply {
                    text = value
                    setTextAppearance(R.style.TextAppearance_Sushi_Label)
                })
            }
            relayout()
        }

    init {
        orientation = LinearLayout.HORIZONTAL

        context.theme.obtainStyledAttributes(
            attrs,
            com.zomato.sushilib.R.styleable.SushiLabeledStrip,
            defStyleAttr,
            defStyleRes
        ).let {
            text = it.getString(R.styleable.SushiLabeledStrip_text)
            labelText = it.getString(R.styleable.SushiLabeledStrip_labelText)
            it.recycle()

            initialized = true
            relayout()
        }
    }

    private fun relayout() {
        if (!initialized) return

        removeAllViews()
        textView?.let {
            addView(textView, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f))
        }
        labelTextView?.let {
            addView(labelTextView)
        }
    }
}