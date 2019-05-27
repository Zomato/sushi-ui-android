package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.zomato.sushilib.R

/**
 * Created by prempal on 2019-05-27.
 */
open class SushiCheckableStripGroup @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr) {

    private var minChecked = 0
    private var maxChecked = -1
    private var checkedCount = 0

    private var checkAllowedListener: SushiCheckBox.CheckAllowedListener
    private var childOnCheckedChangeListener: SushiCheckableStrip.OnCheckedChangeListener

    constructor(ctx: Context, minChecked: Int, maxChecked: Int) : this(ctx) {
        this.minChecked = minChecked
        this.maxChecked = maxChecked
    }

    init {
        orientation = VERTICAL
        checkAllowedListener = object : SushiCheckBox.CheckAllowedListener {
            override fun allowCheck(isChecked: Boolean): Boolean {
                return if (isChecked || maxChecked == -1) {
                    true
                } else {
                    checkedCount < maxChecked
                }
            }
        }
        childOnCheckedChangeListener = object : SushiCheckableStrip.OnCheckedChangeListener {
            override fun onCheckedChanged(view: SushiCheckableStrip, isChecked: Boolean) {
                if (isChecked) checkedCount++ else checkedCount--
            }
        }
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiCheckableStripGroup,
            defStyleAttr, 0
        ).let {
            minChecked = it.getInt(R.styleable.SushiCheckableStripGroup_minChecked, minChecked)
            maxChecked = it.getInt(R.styleable.SushiCheckableStripGroup_maxChecked, maxChecked)
            it.recycle()
        }
    }

    fun isValid(): Boolean {
        return checkedCount >= minChecked && (maxChecked == -1 || checkedCount <= maxChecked)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        (child as? SushiCheckableStrip)?.let {
            val id = child.id
            if (id == View.NO_ID) {
                child.id = View.generateViewId()
            }
            if (it.isChecked) {
                if (checkedCount < maxChecked) {
                    checkedCount++
                } else {
                    it.isChecked = false
                }
            }
            it.setCheckAllowedListener(checkAllowedListener)
            it.setOnCheckedChangeListener(childOnCheckedChangeListener)
        }
        super.addView(child, index, params)
    }

}