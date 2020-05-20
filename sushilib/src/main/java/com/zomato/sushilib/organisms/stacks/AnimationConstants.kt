package com.zomato.sushilib.organisms.stacks

import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

/**
 * created by championswimmer on 2019-08-01
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
internal object AnimationConstants {
    const val DEFAULT_ANIM_DURATION = 350L
    val DEFAULT_BOUNCE_INTERPOLATOR = BounceInterpolator()
    val DEFAULT_OVERSHOOT_INTERPOLATOR = OvershootInterpolator()
    val DEFAULT_DECELERATE_INTERPOLATOR = DecelerateInterpolator()
    val DEFAULT_EASE_INTERPOLATOR = FastOutSlowInInterpolator()
}