package com.zomato.sushilib.organisms.stacks.page

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.zomato.sushilib.utils.dimens.DimenUtils.dp2px
import com.zomato.sushilib.utils.view.ViewUtils
import com.zomato.sushilib.utils.view.ViewUtils.executeOnMeasure
import java.util.concurrent.atomic.AtomicBoolean

/**
 * created by championswimmer on 2019-07-26
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
internal class PullCollapsibleActivityHelper(val activity: SushiPullCollapsibleActivity) {

    private lateinit var activityPageLayout: StandaloneExpandablePageLayout

    private val BG_COL_TRANS_ALPHA_75 = ColorDrawable(Color.argb(191, 0, 0, 0))
    private val BG_COL_TRANS_ALPHA_0 = ColorDrawable(Color.TRANSPARENT)

    private var expandCalled = false
    private var expandedFromRect: Rect? = null
    private var standardToolbarHeight: Int = 0
    private var pullCollapsibleEnabled = true
    private var wasActivityRecreated: Boolean = false
    private var entryAnimationEnabled = true

    private var bypassHandleFinish = false
    private var isUnFading = AtomicBoolean(false)

    fun onCreate(savedInstanceState: Bundle?) {
        setPullToCollapseEnabled(
            activity.intent.getBooleanExtra(
                SushiPullCollapsibleActivity.EXTRA_ENABLE_PULL_COLLAPSE,
                false
            )
        )

        activity.apply {
            wasActivityRecreated = savedInstanceState == null

            if (entryAnimationEnabled && pullCollapsibleEnabled) {
                overridePendingTransition(0, 0)
            }

            standardToolbarHeight = ViewUtils.toolbarHeight(this)
        }
    }

    fun onStart() {
        if (expandCalled.not()) {
            throw AssertionError("This activity wasn't expanded when it was created " +
                "You have to call setContentView either through conventional way or through passing null ")
        }
    }

    fun setEntryAnimationEnabled(entryAnimationEnabled: Boolean) {
        this.entryAnimationEnabled = entryAnimationEnabled
    }

    /**
     * Defaults to true. When disabled, this behaves like a normal Activity with no expandable page animations.
     * Should be called before onCreate().
     */
    protected fun setPullToCollapseEnabled(enabled: Boolean) {
        pullCollapsibleEnabled = enabled
    }

    public fun expand() {
        if (!pullCollapsibleEnabled) {
            expandCalled = true
            return
        }

        activity.apply {
            intent.getParcelableExtra<Rect>(SushiPullCollapsibleActivity.EXTRA_EXPAND_FROM_RECT)?.let {
                expandFrom(it)
            } ?: expandFromBottom()
        }
    }

    fun setContentView(layoutResID: Int): View? {
        if (!pullCollapsibleEnabled) {
            return null
        }
        val parent = activity.findViewById<ViewGroup>(android.R.id.content)
        val view = activity.layoutInflater.inflate(layoutResID, parent, false)
        return setContentView(view)
    }

    fun setContentView(view: View): View {
        if (!pullCollapsibleEnabled) {
            return view
        }
        activityPageLayout = wrapInExpandablePage(view)
        return activityPageLayout
    }

    fun handleFinish(): Boolean {
        // Note to self: It's important to check if expandedFromRect != null
        // and not expandCalled, because expandCalled gets set after the page
        // layout gets measured.
        if (bypassHandleFinish) {
            bypassHandleFinish = false
            return false
        }

        if (pullCollapsibleEnabled && expandedFromRect != null) {
            activityPageLayout.collapseTo(expandedFromRect!!)
            return true
        }

        return false
    }

    private val bgTransUnFade = TransitionDrawable(arrayOf(
        BG_COL_TRANS_ALPHA_0, BG_COL_TRANS_ALPHA_75
    ))

    private fun StandaloneExpandablePageLayout.fadeBg() {
        background = BG_COL_TRANS_ALPHA_0
    }

    private fun StandaloneExpandablePageLayout.unFadeBg() {
        if (background != bgTransUnFade && !isUnFading.getAndSet(true)) {
            bgTransUnFade.resetTransition()
            background = bgTransUnFade
            bgTransUnFade.startTransition(300)
            isUnFading.compareAndSet(true, false)
        }
    }

    private fun wrapInExpandablePage(view: View): StandaloneExpandablePageLayout {
        activity.apply {
            val pageLayout = StandaloneExpandablePageLayout(this)
            pageLayout.elevation = dp2px(view.context, 0f)
            pageLayout.background = null

            window.setBackgroundDrawable(BG_COL_TRANS_ALPHA_0)
            window.transitionBackgroundFadeDuration = 200

            if (pullCollapsibleEnabled) {
                pageLayout.pullToCollapseThresholdDistance = standardToolbarHeight
                pageLayout.callbacks = object : StandaloneExpandablePageLayout.Callbacks {
                    override fun onPageAboutToExpand(animDuration: Long) {
                    }

                    override fun onPageAboutToCollapse(animDuration: Long) {
                        pageLayout.fadeBg()
                    }

                    override fun onPull(deltaY: Float, currentTranslationY: Float, upwardPull: Boolean,
                                        deltaUpwardPull: Boolean, collapseEligible: Boolean) {

                        pageLayout.fadeBg()
                        // call the activity's onPull
                        activity.onPull(deltaY, currentTranslationY, upwardPull, deltaUpwardPull, collapseEligible)
                    }

                    override fun onPageRelease(collapseEligible: Boolean) {
                        activity.onPageRelease(collapseEligible)
                        if (collapseEligible) {
                            finish()
                        }
                    }

                    override fun onPageCollapsed() {
                        bypassHandleFinish = true
                        activity.finish()
                        overridePendingTransition(0, 0)
                    }

                    override fun onPageExpanded() {
                        pageLayout.unFadeBg()
                    }
                }
            } else {
                pageLayout.pullToCollapseEnabled = false
                pageLayout.expandImmediately()
            }

            pageLayout.addView(view)
            return pageLayout
        }
    }

    protected fun expandFromTop() {
        expandCalled = true
        activityPageLayout.executeOnMeasure {
            val toolbarRect = Rect(0, standardToolbarHeight, activityPageLayout.width, standardToolbarHeight)
            expandFrom(toolbarRect)
        }
    }

    protected fun expandFromBottom() {
        expandCalled = true
        activityPageLayout.executeOnMeasure {
            val toolbarRect = Rect(
                0,
                activity.window.decorView.height,
                activityPageLayout.width,
                activity.window.decorView.height
            )
            expandFrom(toolbarRect)
        }
    }

    protected fun expandFrom(fromRect: Rect) {
        expandCalled = true

        expandedFromRect = fromRect
        activityPageLayout.executeOnMeasure {
            if (wasActivityRecreated) {
                activityPageLayout.expandFrom(fromRect)
            } else {
                activityPageLayout.expandImmediately()
            }
        }
    }

    private fun windowBackgroundFromTheme(): Drawable {
        val attributes = TypedValue()
        activity.theme.resolveAttribute(android.R.attr.windowBackground, attributes, true)
        val isColorInt =
            attributes.type >= TypedValue.TYPE_FIRST_COLOR_INT && attributes.type <= TypedValue.TYPE_LAST_COLOR_INT

        return when {
            isColorInt -> ColorDrawable(attributes.data)
            else -> ContextCompat.getDrawable(activity, attributes.resourceId)!!
        }
    }
}