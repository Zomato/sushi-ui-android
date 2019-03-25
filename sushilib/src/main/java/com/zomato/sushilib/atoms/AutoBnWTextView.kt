package com.zomato.sushilib.atoms

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R

class AutoBnWTextView : TextView {
    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)



    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // TODO: This should also react when background changes
        setAutoContrastColor()
    }

    private fun setAutoContrastColor () {
        if (background is ColorDrawable) {
            val c = (background as ColorDrawable).color

            if (Color.red(c) + Color.green(c) + Color.blue(c) < 300) {
                setTextColor(resources.getColor(R.color.sushi_grey_100))
            } else {
                setTextColor(resources.getColor(R.color.sushi_black))
            }
        }
    }
}