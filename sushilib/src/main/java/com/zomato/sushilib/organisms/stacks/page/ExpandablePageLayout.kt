package com.zomato.sushilib.organisms.stacks.page

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.zomato.sushilib.organisms.stacks.ExpandedItem
import com.zomato.sushilib.organisms.stacks.InternalPageCallbacks
import com.zomato.sushilib.organisms.stacks.executeOnMeasure
import com.zomato.sushilib.organisms.stacks.withEndAction
import java.lang.reflect.Method

open class ExpandablePageLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseExpandablePageLayout(context, attrs), PullToCollapseListener.OnPullListener {
    val TAG = "EXPAGE"

    /** Alpha of this page when it's collapsed. */
    internal var collapsedAlpha = 0F

    var pullToCollapseInterceptor: OnPullToCollapseInterceptor = IGNORE_ALL_PULL_TO_COLLAPSE_INTERCEPTOR

    /** Minimum Y-distance the page has to be pulled before it's eligible for collapse. */
    var pullToCollapseThresholdDistance: Int
        get() = pullToCollapseListener.collapseDistanceThreshold
        set(value) {
            pullToCollapseListener.collapseDistanceThreshold = value
        }

    var pullToCollapseEnabled = false
    val pullToCollapseListener: PullToCollapseListener
    lateinit var currentState: PageState

    internal var internalStateCallbacksForRecyclerView: InternalPageCallbacks = InternalPageCallbacks.NoOp()
    private var internalStateCallbacksForNestedPage: InternalPageCallbacks = InternalPageCallbacks.NoOp()
    private var stateChangeCallbacks: MutableList<PageStateChangeCallbacks> = ArrayList(4)

    private var nestedPage: ExpandablePageLayout? = null
    private val expandedAlpha = 1F
    private val threshHoldAlpha = 0.8F
    private var isFullyCoveredByNestedPage = false

    val isExpanded: Boolean
        get() = currentState == PageState.EXPANDED

    val isExpandingOrCollapsing: Boolean
        get() = currentState == PageState.EXPANDING || currentState == PageState.COLLAPSING

    val isCollapsing: Boolean
        get() = currentState == PageState.COLLAPSING

    val isCollapsed: Boolean
        get() = currentState == PageState.COLLAPSED

    val isExpanding: Boolean
        get() = currentState == PageState.EXPANDING

    val isExpandedOrExpanding: Boolean
        get() = currentState == PageState.EXPANDED || currentState == PageState.EXPANDING

    val isCollapsedOrCollapsing: Boolean
        get() = currentState == PageState.COLLAPSING || currentState == PageState.COLLAPSED

    enum class PageState {
        COLLAPSING,
        COLLAPSED,
        EXPANDING,
        EXPANDED
    }

    init {
        // Hidden on start.
        alpha = expandedAlpha
        visibility = View.INVISIBLE
        changeState(PageState.COLLAPSED)

        pullToCollapseEnabled = true
        pullToCollapseListener = PullToCollapseListener(getContext(), this)
        pullToCollapseListener.addOnPullListener(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        // Cache before-hand.
        Thread {
            if (suppressLayoutMethod == null) {
                setSuppressLayoutMethodUsingReflection(this, false)
            }
        }.start()
    }

    override fun onDetachedFromWindow() {
        pullToCollapseInterceptor = IGNORE_ALL_PULL_TO_COLLAPSE_INTERCEPTOR
        nestedPage = null
        internalStateCallbacksForNestedPage = InternalPageCallbacks.NoOp()
        internalStateCallbacksForRecyclerView = InternalPageCallbacks.NoOp()
        stateChangeCallbacks.clear()
        super.onDetachedFromWindow()
    }

    private fun changeState(newPageState: PageState) {
        currentState = newPageState
    }

    override fun hasOverlappingRendering(): Boolean {
        // This should help improve performance when animating alpha.
        // Source: https://www.youtube.com/watch?v=wIy8g8yNhNk.
        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        // Ignore touch events until the page is
        // fully expanded for avoiding accidental taps.
        if (isExpanded) {
            super.dispatchTouchEvent(ev)
        }

        // Consume all touch events to avoid them leaking behind.
        return true
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        var intercepted = false
        if (pullToCollapseEnabled && visibility == View.VISIBLE) {
            intercepted = pullToCollapseListener.onTouch(this, event)
        }

        return intercepted || super.onInterceptTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val handled = pullToCollapseEnabled && pullToCollapseListener.onTouch(this, event)
        return handled || super.onTouchEvent(event)
    }

    override fun onPull(
        deltaY: Float,
        currentTranslationY: Float,
        upwardPull: Boolean,
        deltaUpwardPull: Boolean,
        collapseEligible: Boolean
    ) {
        // Sync the positions of the list items with this page.
        dispatchOnPagePullCallbacks(deltaY, currentTranslationY)
    }

    override fun onRelease(collapseEligible: Boolean) {
        dispatchOnPageReleaseCallback(collapseEligible)

        if (!collapseEligible.not()) {
            return
        }

        if (isCollapsedOrCollapsing) {
            // collapse() got called somehow.
            // Let the page collapse in peace.
            return
        }

        changeState(PageState.EXPANDED)
        stopAnyOngoingPageAnimation()

        // Restore everything to their expanded position.

        // Expand page again.
        if (translationY != 0F) {
            animate()
                .withLayer()
                .translationY(0F)
                .alpha(expandedAlpha)
                .setDuration(animationDurationMillis)
                .setInterpolator(animationInterpolator)
                .withEndAction { dispatchOnPageFullyCoveredCallback() }
                .start()
        }
    }

    /**
     * Expands this page (with animation) so that it fills the whole screen.
     */
    internal fun expand(expandedItem: ExpandedItem) {
        if (isLaidOut.not() && visibility != View.GONE) {
            throw IllegalAccessError("Width / Height not available to expand")
        }

        // Ignore if already expanded.
        if (isExpandedOrExpanding) {
            return
        }

        // Place the expandable page on top of the expanding item.
        alignPageWithExpandingItem(expandedItem)

        // Callbacks, just before the animation starts.
        dispatchOnPageAboutToExpandCallback(animationDurationMillis)

        // Animate!
        animatePageExpandCollapse(true, width, height, expandedItem)
    }

    /**
     * Expand this page instantly, without any animation.
     */
    internal fun expandImmediately() {
        if (currentState == PageState.EXPANDING || currentState == PageState.EXPANDED) {
            return
        }

        visibility = View.VISIBLE
        alpha = expandedAlpha

        executeOnMeasure {
            // Cover the whole screen right away. Don't need any animations.
            alignPageToCoverScreen()
            dispatchOnPageAboutToExpandCallback(0)
            dispatchOnPageFullyExpandedCallback()
        }
    }

    /**
     * Collapses this page, back to its original state.
     */
    internal fun collapse(expandedItem: ExpandedItem) {
        if (currentState == PageState.COLLAPSED || currentState == PageState.COLLAPSING) {
            return
        }

        var targetWidth = expandedItem.expandedItemLocationRect.width()
        val targetHeight = expandedItem.expandedItemLocationRect.height()
        if (targetWidth == 0) {
            // Page must have expanded immediately after a state restoration.
            targetWidth = width
        }
        animatePageExpandCollapse(false, targetWidth, targetHeight, expandedItem)

        // Send state callbacks that the city is going to collapse.
        dispatchOnPageAboutToCollapseCallback()
    }

    /**
     * Place the expandable page exactly on top of the expanding item.
     */
    private fun alignPageWithExpandingItem(expandedItem: ExpandedItem) {
        // Match height and location.
        setClippedDimensions(
            expandedItem.expandedItemLocationRect.width(),
            expandedItem.expandedItemLocationRect.height()
        )
        translationY = expandedItem.expandedItemLocationRect.top.toFloat()
    }

    internal fun alignPageToCoverScreen() {
        resetClipping()
        translationY = 0F
    }

    internal fun animatePageExpandCollapse(
        expand: Boolean,
        targetWidth: Int,
        targetHeight: Int,
        expandedItem: ExpandedItem
    ) {
        var targetPageTranslationY = if (expand) 0F else expandedItem.expandedItemLocationRect.top.toFloat()
        val targetPageTranslationX = if (expand) 0F else expandedItem.expandedItemLocationRect.left.toFloat()

        if (expand.not()) {
            setSuppressLayoutMethodUsingReflection(this, true)
        }

        if (expand) {
            visibility = View.VISIBLE
        }

        alpha = if (expand) collapsedAlpha else threshHoldAlpha
        stopAnyOngoingPageAnimation()
        animate()
            .withLayer()
            .alpha(if (expand) expandedAlpha else collapsedAlpha)
            .translationY(targetPageTranslationY)
            .translationX(targetPageTranslationX)
            .setDuration(animationDurationMillis)
            .setInterpolator(animationInterpolator)
            .withEndAction { canceled ->
                setSuppressLayoutMethodUsingReflection(this@ExpandablePageLayout, false)

                if (!canceled) {
                    if (!expand) {
                        visibility = View.INVISIBLE
                        dispatchOnPageCollapsedCallback()
                    } else {
                        dispatchOnPageFullyExpandedCallback()
                    }
                }
            }
            .start()

        animateDimensions(targetWidth, targetHeight)
    }

    internal fun stopAnyOngoingPageAnimation() {
        animate().cancel()
    }

    override fun draw(canvas: Canvas) {
        if (currentState == PageState.COLLAPSED) {
            return
        }
        super.draw(canvas)
    }

    override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
        // When this page is fully covered by a nested ExpandablePage, avoid drawing any other child Views.
        return if (isFullyCoveredByNestedPage && child !is ExpandablePageLayout) {
            false
        } else {
            super.drawChild(canvas, child, drawingTime)
        }
    }

    private fun dispatchOnPagePullCallbacks(deltaY: Float, translationY: Float) {
        internalStateCallbacksForNestedPage.onPagePull(deltaY, translationY)
        internalStateCallbacksForRecyclerView.onPagePull(deltaY, translationY)
    }

    private fun dispatchOnPageReleaseCallback(collapseEligible: Boolean) {
        internalStateCallbacksForNestedPage.onPageRelease(collapseEligible)
        internalStateCallbacksForRecyclerView.onPageRelease(collapseEligible)
    }

    private fun dispatchOnPageAboutToExpandCallback(expandAnimDuration: Long) {
        internalStateCallbacksForNestedPage.onPageAboutToExpand()
        internalStateCallbacksForRecyclerView.onPageAboutToExpand()

        for (i in stateChangeCallbacks.indices.reversed()) {
            stateChangeCallbacks[i].onPageAboutToExpand(expandAnimDuration)
        }

        onPageAboutToExpand(animationDurationMillis)

        // The state change must happen after the subscribers have been
        // notified that the page is going to expand.
        changeState(PageState.EXPANDING)
    }

    private fun dispatchOnPageFullyExpandedCallback() {
        changeState(PageState.EXPANDED)
        dispatchOnPageFullyCoveredCallback()

        for (i in stateChangeCallbacks.indices.reversed()) {
            stateChangeCallbacks[i].onPageExpanded()
        }

        onPageExpanded()
    }

    /**
     * There's a difference between the page fully expanding and fully covering the list.
     * When the page is fully expanded, it may or may not be covering the list. This is
     * usually when the user is pulling the page.
     */
    private fun dispatchOnPageFullyCoveredCallback() {
        internalStateCallbacksForNestedPage.onPageFullyCovered()
        internalStateCallbacksForRecyclerView.onPageFullyCovered()
    }

    private fun dispatchOnPageAboutToCollapseCallback() {
        internalStateCallbacksForNestedPage.onPageAboutToCollapse()
        internalStateCallbacksForRecyclerView.onPageAboutToCollapse()

        for (i in stateChangeCallbacks.indices.reversed()) {
            stateChangeCallbacks[i].onPageAboutToCollapse(animationDurationMillis)
        }

        onPageAboutToCollapse(animationDurationMillis)

        // The state change must happen after the subscribers have been
        // notified that the page is going to collapse.
        changeState(PageState.COLLAPSING)
    }

    private fun dispatchOnPageCollapsedCallback() {
        changeState(PageState.COLLAPSED)

        internalStateCallbacksForNestedPage.onPageCollapsed()
        internalStateCallbacksForRecyclerView.onPageCollapsed()

        for (i in stateChangeCallbacks.indices.reversed()) {
            stateChangeCallbacks[i].onPageCollapsed()
        }
        onPageCollapsed()
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected open fun onPageAboutToExpand(expandAnimDuration: Long) {
        // For rent.
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected open fun onPageExpanded() {
        // For rent.
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected open fun onPageAboutToCollapse(collapseAnimDuration: Long) {
        // For rent.
    }

    /**
     * Page is totally invisible to the user when this is called.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    protected open fun onPageCollapsed() {
        // For rent.
    }

    /**
     * Offer a pull-to-collapse to a listener if it wants to block it. If a nested page is registered
     * and the touch was made on it, block it right away.
     */
    internal fun handleOnPullToCollapseIntercept(
        event: MotionEvent,
        downX: Float,
        downY: Float,
        deltaUpwardSwipe: Boolean
    ): InterceptResult {
        val nestedPageCopy = nestedPage

        return if (nestedPageCopy != null
            && nestedPageCopy.isExpandedOrExpanding
            && nestedPageCopy.clippedDimens.contains(downX.toInt(), downY.toInt())
        ) {
            // Block this pull if it was made inside a nested page. Let the nested
            // page's pull-listener consume this event. I should use nested scrolling
            // in the future to make this smarter.
            // TODO: 20/03/17 Do we even need to call the nested page's listener?
            nestedPageCopy.handleOnPullToCollapseIntercept(event, downX, downY, deltaUpwardSwipe)
            InterceptResult.INTERCEPTED

        } else run {
            pullToCollapseInterceptor(downX, downY, deltaUpwardSwipe)
        }
    }

    fun addStateChangeCallbacks(callbacks: PageStateChangeCallbacks) {
        stateChangeCallbacks.add(callbacks)
    }

    fun removeStateChangeCallbacks(callbacks: PageStateChangeCallbacks) {
        stateChangeCallbacks.remove(callbacks)
    }

    /**
     * Listener that gets called when this page is being pulled.
     */
    fun addOnPullListener(listener: PullToCollapseListener.OnPullListener) {
        pullToCollapseListener.addOnPullListener(listener)
    }

    fun removeOnPullListener(pullListener: PullToCollapseListener.OnPullListener) {
        pullToCollapseListener.removeOnPullListener(pullListener)
    }

    companion object {

        private var suppressLayoutMethod: Method? = null

        // TODO: Move to a different class.
        private fun setSuppressLayoutMethodUsingReflection(layout: ExpandablePageLayout, suppress: Boolean) {
            try {
                if (suppressLayoutMethod == null) {
                    suppressLayoutMethod =
                        ViewGroup::class.java.getMethod("suppressLayout", Boolean::class.javaPrimitiveType)
                }
                suppressLayoutMethod!!.invoke(layout, suppress)
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
