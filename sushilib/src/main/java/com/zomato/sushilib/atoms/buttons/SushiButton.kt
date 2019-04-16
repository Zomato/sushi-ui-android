package com.zomato.sushilib.atoms.buttons

import android.content.Context
import android.support.annotation.StyleRes
import android.support.design.button.MaterialButton
import android.util.AttributeSet
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemeWrappedContext

class SushiButton @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : MaterialButton(
    getThemeWrappedContext(ctx, defStyleRes),
    attrs,
    defStyleAttr
) {

}