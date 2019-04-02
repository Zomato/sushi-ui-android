package com.zomato.sushilib.atoms.views

import android.view.ViewOutlineProvider

// todo rename
interface RoundedView {
    val imageOutlineProvider: SushiViewOutlineProvider
    var cornerRadius: Float
        set(value) {
            notifyOutlineProvider(value)
            setClipToOutline(true)
        }
        get() = imageOutlineProvider.cornerRadius

    private fun notifyOutlineProvider(cornerRad: Float = cornerRadius) {
        imageOutlineProvider.apply {
            this.cornerRadius = cornerRad
        }
        setOutlineProvider(imageOutlineProvider)
    }

    fun setOutlineProvider(outlineProvider: ViewOutlineProvider)
    fun setClipToOutline(clipToOutline: Boolean)
}