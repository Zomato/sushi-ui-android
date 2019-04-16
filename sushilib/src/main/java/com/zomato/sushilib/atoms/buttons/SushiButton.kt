package com.zomato.sushilib.atoms.buttons

import android.content.Context
import android.support.design.button.MaterialButton
import android.support.v7.view.ContextThemeWrapper
import android.util.AttributeSet

class SushiButton @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : MaterialButton(
    (if (defStyleRes == 0) { ctx } else { ContextThemeWrapper(ctx, defStyleRes)}),
    attrs,
    defStyleAttr) {

}