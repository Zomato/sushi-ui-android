package com.zomato.sushilib.organisms.navigation

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import com.google.android.material.tabs.TabLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemeWrappedContext

/**
 * created by championswimmer on 01/05/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
open class SushiTabLayout @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.tabStyle,
    @StyleRes defStyleRes: Int = 0
) : TabLayout(
    getThemeWrappedContext(ctx, defStyleRes),
    attrs, defStyleAttr
)