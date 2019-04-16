package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.AppCompatTextView
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.widget.ActionMenuView
import android.widget.SearchView
import android.widget.TextView
import com.zomato.sushilib.R

class SushiIcon @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.sushiIconAppearance, defStyleRes: Int = 0
) : SushiTextView(context, attrs, defStyleAttr, defStyleRes)