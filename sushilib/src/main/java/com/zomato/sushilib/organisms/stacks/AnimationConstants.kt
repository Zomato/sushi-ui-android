package com.zomato.sushilib.organisms.stacks

import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator

/**
 * created by championswimmer on 2019-08-01
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
internal object AnimationConstants {
    const val DEFAULT_ANIM_DURATION = 350L
    val DEFAULT_BOUNCE_INTERPOLATOR = BounceInterpolator()
    val DEFAULT_OVERSHOOT_INTERPOLATOR = OvershootInterpolator()
    val DEFAULT_ANTICIPATE_INTERPOLATOR = AnticipateInterpolator()
    val DEFAULT_EASE_INTERPOLATOR = FastOutSlowInInterpolator()
}