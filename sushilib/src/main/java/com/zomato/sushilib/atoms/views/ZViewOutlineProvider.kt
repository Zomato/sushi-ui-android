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
    val outlineType: Int = OutlineType.ROUNDED_RECT
) : ViewOutlineProvider() {

    var cornerRadius: Float = 0f


    override fun getOutline(view: View?, outline: Outline?) {

        view?.also { v ->
            var left = 0
            var top = 0
            var right = v.width
            var bottom = v.height

            when (outlineType) {
                OutlineType.CIRCLE -> {
                    outline?.setOval(left, top, right, bottom)
                }
                OutlineType.ROUNDED_RECT -> {
                    // todo make ovel check add
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