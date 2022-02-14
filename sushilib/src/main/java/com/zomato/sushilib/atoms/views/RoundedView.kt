package com.zomato.sushilib.atoms.views

import android.view.ViewOutlineProvider
import com.zomato.sushilib.annotations.OutlineType
import com.zomato.sushilib.utils.view.SushiViewOutlineProvider

/**
 * created by championswimmer on 05/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
interface RoundedView {
    fun getOutlineProvider(): ViewOutlineProvider
    fun setOutlineProvider(outlineProvider: ViewOutlineProvider)
    fun setClipToOutline(clipToOutline: Boolean)
    fun getClipToOutline(): Boolean

    var cornerRadius: Float
        get() = (getOutlineProvider() as? SushiViewOutlineProvider)?.cornerRadius ?: 0f
        set(cr) {
            // If getOutlineProvider() is a SushiViewOutlineProvider
            (getOutlineProvider() as? SushiViewOutlineProvider)?.apply {
                cornerRadius = cr

                // If getOutlineProvider() turned out null, or not SushiViewOutlineProvider
            } ?: setOutlineProvider(SushiViewOutlineProvider(OutlineType.ROUNDED_RECT, cr))

            setClipToOutline(true)
        }
}