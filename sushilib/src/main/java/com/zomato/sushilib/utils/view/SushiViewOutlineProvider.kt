package com.zomato.sushilib.utils.view

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
class SushiViewOutlineProvider(
    @OutlineType
    val outlineType: Int = OutlineType.ROUNDED_RECT,
    var cornerRadius: Float = 0f,
    var paddingOutside: Boolean = false
) : ViewOutlineProvider() {
    var left: Int = 0
    var top: Int = 0
    var right: Int = 0
    var bottom: Int = 0

    override fun getOutline(view: View?, outline: Outline?) {

        view?.also { v ->
            left = if (paddingOutside) {
                v.paddingLeft
            } else {
                0
            }
            top = if (paddingOutside) {
                v.paddingTop
            } else {
                0
            }
            right = v.width - (if (paddingOutside) {
                v.paddingRight
            } else {
                0
            })
            bottom = v.height - (if (paddingOutside) {
                v.paddingBottom
            } else {
                0
            })

            if (outlineType == OutlineType.CIRCLE) {
                val radius = Math.min((right - left), (bottom - top)) / 2
                val centerX = (right + left) / 2
                val centerY = (bottom + top) / 2
                left = centerX - radius
                top = centerY - radius
                right = centerX + radius
                bottom = centerY + radius
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