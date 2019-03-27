package com.zomato.sushilib.atoms.views

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
class ZViewOutlineProvider(
    @OutlineType var outlineType: Int,
    var cornerRadius: Float = 0f
) : ViewOutlineProvider() {

    override fun getOutline(view: View?, outline: Outline?) {

        view?.also { v ->
            val delta = Math.min(v.height, v.width)
            val left = v.paddingLeft
            val top = v.paddingTop
            val right = delta - v.paddingRight
            val bottom = delta - v.paddingBottom


            when (outlineType) {
                OutlineType.CIRCLE -> {
                    outline?.setOval(left, top, right, bottom)
                }
                OutlineType.ROUNDED_RECT -> {
                    // TODO: When supporting corner flags
//                    left -= cornerRadius.toInt()
//                    top -= cornerRadius.toInt()
//                    right += cornerRadius.toInt()
//                    bottom += cornerRadius.toInt()
                    outline?.setRoundRect(left, top, right, bottom, cornerRadius)
                }
            }
        }
    }
}