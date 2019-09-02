package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.IdRes

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

    /**
     * <p>Returns the identifier of the selected [SushiCheckableStrip] in this group.
     * Upon empty selection, the returned value is -1.</p>
     *
     * @return the unique id of the selected [SushiCheckableStrip] in this group
     */
    @IdRes
    fun getCheckedId() = checkedId

    /**
     * Sets the listener to be called when the checked strip changes in this group.
     *
     * @param listener The listener.
     */
    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        onCheckedChangeListener = listener
    }

    /**
     * Sets the lambda to be called when the checked strip changes in this group.
     *
     * @param listener The lambda.
     */
    fun setOnCheckedChangeListener(listener: (group: SushiCheckableStripRadioGroup, id: Int, isChecked: Boolean) -> Unit) {
        setOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChange(group: SushiCheckableStripRadioGroup, id: Int, isChecked: Boolean) =
                listener(group, checkedId, isChecked)
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

    override fun onViewRemoved(child: View?) {
        (child as? SushiCheckableStrip)?.let {
            if (it.isChecked) {
                checkedId = View.NO_ID
            }
            it.setOnCheckedChangeListener(null)
        }
        super.onViewRemoved(child)
    }

    private fun setCheckedId(@IdRes id: Int) {
        checkedId = id
        onCheckedChangeListener?.onCheckedChange(this, id, true)
    }

    private fun setCheckedStateForView(@IdRes viewId: Int, checked: Boolean) {
        val checkedView = findViewById<SushiCheckableStrip>(viewId)
        checkedView?.isChecked = checked
        onCheckedChangeListener?.onCheckedChange(this, viewId, checked)
    }

    /**
     * Interface definition for a callback to be invoked when the checked strip changes in this group.
     */
    interface OnCheckedChangeListener {
        /**
         * Called when the checked strip changes in this group.
         *
         * @param group the group in which the checked strip has changed
         * @param id the unique identifier of the checked strip
         * @param isChecked the new checked state
         */
        fun onCheckedChange(group: SushiCheckableStripRadioGroup, @IdRes id: Int, isChecked: Boolean)
    }

}