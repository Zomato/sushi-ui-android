package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.support.annotation.IdRes
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

    private var checkChangeAllowedListener: SushiCheckBox.CheckChangeAllowedListener
    private var childOnCheckedChangeListener: SushiCheckableStrip.OnCheckedChangeListener

    private var onCheckedChangeListener: OnCheckedChangeListener? = null
    private var onMaxCheckedReachedListener: OnMaxCheckedReachedListener? = null

    constructor(ctx: Context, minChecked: Int, maxChecked: Int) : this(ctx) {
        this.minChecked = minChecked
        this.maxChecked = maxChecked
    }

    init {
        orientation = VERTICAL
        checkChangeAllowedListener = object : SushiCheckBox.CheckChangeAllowedListener {
            override fun isCheckChangeAllowed(isChecked: Boolean): Boolean {
                return if (isChecked || maxChecked == -1) {
                    true
                } else {
                    if (checkedCount < maxChecked) {
                        true
                    } else {
                        onMaxCheckedReachedListener?.onMaxCheckedReached()
                        false
                    }
                }
            }
        }
        childOnCheckedChangeListener = object : SushiCheckableStrip.OnCheckedChangeListener {
            override fun onCheckedChanged(view: SushiCheckableStrip, isChecked: Boolean) {
                if (isChecked) checkedCount++ else checkedCount--
                onCheckedChangeListener?.onCheckedChange(this@SushiCheckableStripGroup, view.id, isChecked)
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

    /**
     * Sets the listener to be called when the group has reached the max number of checked strips.
     *
     * @param listener The listener.
     */
    fun setOnMaxCheckedReachedListener(listener: OnMaxCheckedReachedListener?) {
        onMaxCheckedReachedListener = listener
    }

    /**
     * Sets the lambda to be called when the group has reached the max number of checked strips.
     *
     * @param listener The lambda.
     */
    fun setOnMaxCheckedReachedListener(listener: () -> Unit) {
        setOnMaxCheckedReachedListener(object : OnMaxCheckedReachedListener {
            override fun onMaxCheckedReached() = listener()
        })
    }

    /**
     * Sets the listener to be called when a checked strip changes its checked state in this group.
     *
     * @param listener The listener.
     */
    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        onCheckedChangeListener = listener
    }

    /**
     * Sets the lambda to be called when a checked strip changes its checked state in this group.
     *
     * @param listener The lambda.
     */
    fun setOnCheckedChangeListener(listener: (group: SushiCheckableStripGroup, id: Int, isChecked: Boolean) -> Unit) {
        setOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChange(group: SushiCheckableStripGroup, id: Int, isChecked: Boolean) =
                listener(group, id, isChecked)
        })
    }

    /**
     * Sets the minimum number of checked items allowed by this group.
     *
     * @param minChecked minimum number of checked items
     */
    fun setMinChecked(minChecked: Int) {
        this.minChecked = minChecked
    }

    /**
     * Sets the maximum number of checked items allowed by this group.
     *
     * @param maxChecked maximum number of checked items
     */
    @Throws(IllegalArgumentException::class)
    fun setMaxChecked(maxChecked: Int) {
        if (checkedCount <= maxChecked) {
            this.maxChecked = maxChecked
        } else throw IllegalArgumentException("Existing checked items in group greater than maxChecked")
    }

    /**
     * Returns whether the group is in valid state or not.
     * The group is in valid state if the number of checked items in the group is within the set allowed range.
     *
     * @return boolean indicating whether the group is in valid state or not
     */
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
            it.setCheckChangeAllowedListener(checkChangeAllowedListener)
            it.setOnCheckedChangeListener(childOnCheckedChangeListener)
        }
        super.addView(child, index, params)
    }

    override fun onViewRemoved(child: View?) {
        (child as? SushiCheckableStrip)?.let {
            if (it.isChecked) {
                checkedCount--
            }
            it.setOnCheckedChangeListener(null)
            it.setCheckChangeAllowedListener(null)
        }
        super.onViewRemoved(child)
    }

    /**
     * Interface definition for a callback to be invoked when a checked strip changes its checked state in this group.
     */
    interface OnCheckedChangeListener {
        /**
         * Called when a checked strip changes its checked state in this group
         *
         * @param group the group in which a checked strip has changed
         * @param id the unique identifier of the checked strip
         * @param isChecked the new checked state
         */
        fun onCheckedChange(group: SushiCheckableStripGroup, @IdRes id: Int, isChecked: Boolean)
    }

    /**
     * Interface definition for a callback to be invoked when the group has reached the max number of checked strips.
     */
    interface OnMaxCheckedReachedListener {
        /**
         * Called when the group has reached the max number of checked strips
         */
        fun onMaxCheckedReached()
    }
}