package com.zomato.sushilib.atoms.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
class ZCircleView : View {


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) :
            this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            this(context, attrs, defStyleAttr, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {


        outlineProvider = ZViewOutlineProvider(OutlineType.CIRCLE)
        clipToOutline = true
        invalidateOutline()

    }


}