package com.zomato.sushilib.atoms

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R

class ZTextView: TextView {
    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
//        context?.theme?.obtainStyledAttributes(
//            attrs,
//            arrayOf(R.styleable.ZTextView_textSize).toIntArray(),
//            defStyleAttr,
//            R.style.SushiTheme
//        )?.let {
//            val textSize = it.getIn(R.styleable.ZTextView_textSize, 14f)
//        }
    }
}