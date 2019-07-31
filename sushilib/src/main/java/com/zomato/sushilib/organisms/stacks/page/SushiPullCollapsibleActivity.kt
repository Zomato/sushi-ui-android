package com.zomato.sushilib.organisms.stacks.page

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup

/**
 * An Activity that can be dismissed by pulling it vertically.
 * Requires these these properties to be present in the Activity theme:
 *
 * <item name="android:windowIsTranslucent">true</item>
 * <item name="android:colorBackgroundCacheHint">@null</item>
 */
open class SushiPullCollapsibleActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_EXPAND_FROM_RECT = "expand_from_rect"
        const val EXTRA_ENABLE_PULL_COLLAPSE = "enable_pull_collapse"
    }

    private val pcaHelper = PullCollapsibleActivityHelper(this)

    /**
     * Called when the user is pulling down / up the expandable page or the list.
     *
     * @param deltaY              Delta translation-Y since the last onPull call.
     * @param currentTranslationY Current translation-Y of the page.
     * @param upwardPull          Whether or not the page is being pulled in the upward direction.
     * @param deltaUpwardPull     Whether or not the last delta-pull was made in the upward direction.
     * @param collapseEligible    Whether or not the pull distance was enough to trigger a collapse.
     */
    open fun onPull(deltaY: Float, currentTranslationY: Float,
                      upwardPull: Boolean, deltaUpwardPull: Boolean, collapseEligible: Boolean) {
        // available for rent

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pcaHelper.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        pcaHelper.onStart()
    }

    override fun setContentView(layoutResID: Int) {
        pcaHelper.setContentView(layoutResID)?.let {
            super.setContentView(it)
        } ?: super.setContentView(layoutResID)
        pcaHelper.expand()
    }

    override fun setContentView(view: View) {
        super.setContentView(pcaHelper.setContentView(view))
        pcaHelper.expand()
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        super.setContentView(pcaHelper.setContentView(view), params)
        pcaHelper.expand()
    }

    override fun finish() {
        if (!pcaHelper.handleFinish()) {
            super.finish()
        }
    }
}