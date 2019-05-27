package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.support.annotation.IdRes
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * Created by prempal on 2019-05-23.
 */
open class SushiCheckableStripRadioGroup @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr) {

    private var checkedId = View.NO_ID
    private var protectFromCheckedChange = false
    private var childOnCheckedChangeListener: SushiCheckableStrip.OnCheckedChangeListener
    private var onCheckedChangeListener: OnCheckedChangeListener? = null

    init {
        orientation = VERTICAL
        childOnCheckedChangeListener = object : SushiCheckableStrip.OnCheckedChangeListener {
            override fun onCheckedChanged(view: SushiCheckableStrip, isChecked: Boolean) {
                if (protectFromCheckedChange) {
                    return
                }

                protectFromCheckedChange = true
                if (checkedId != View.NO_ID) {
                    setCheckedStateForView(checkedId, false)
                }
                protectFromCheckedChange = false

                setCheckedId(view.id)
            }
        }
    }

    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        onCheckedChangeListener = listener
    }

    fun setOnCheckedChangeListener(listener: (group: SushiCheckableStripRadioGroup, checkedId: Int) -> Unit) {
        setOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChange(group: SushiCheckableStripRadioGroup, checkedId: Int) =
                listener(group, checkedId)
        })
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        (child as? SushiCheckableStrip)?.let {
            val id = child.id
            if (id == View.NO_ID) {
                child.id = View.generateViewId()
            }
            it.setOnCheckedChangeListener(childOnCheckedChangeListener)
            if (it.isChecked) {
                protectFromCheckedChange = true
                if (checkedId != View.NO_ID) {
                    setCheckedStateForView(checkedId, false)
                }
                protectFromCheckedChange = false
                setCheckedId(it.id)
            }
        }
        super.addView(child, index, params)
    }

    private fun setCheckedId(@IdRes id: Int) {
        checkedId = id
        onCheckedChangeListener?.onCheckedChange(this, id)
    }

    private fun setCheckedStateForView(@IdRes viewId: Int, checked: Boolean) {
        val checkedView = findViewById<SushiCheckableStrip>(viewId)
        checkedView?.isChecked = checked
    }

    interface OnCheckedChangeListener {
        fun onCheckedChange(group: SushiCheckableStripRadioGroup, @IdRes checkedId: Int)
    }

}