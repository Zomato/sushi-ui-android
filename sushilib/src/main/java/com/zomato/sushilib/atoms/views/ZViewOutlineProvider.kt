package com.zomato.sushilib.atoms.views

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
class ZViewOutlineProvider(
    @OutlineType
    val outlineType: Int = OutlineType.ROUNDED_RECT,
    var cornerRadius: Float = 0f
) : ViewOutlineProvider() {

    override fun getOutline(view: View?, outline: Outline?) {

        view?.also { v ->
            var left = v.paddingLeft
            var top = v.paddingTop
            var right = v.width - v.paddingRight
            var bottom = v.height - v.paddingBottom

            if (outlineType == OutlineType.CIRCLE) {
                val delta = Math.min(v.height, v.width)
                right = delta - v.paddingRight
                bottom = delta - v.paddingBottom
            }

            when (outlineType) {
                OutlineType.CIRCLE -> {
                    outline?.setOval(left, top, right, bottom)
                }
                OutlineType.ROUNDED_RECT -> {
                    // TODO: Add oval check
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