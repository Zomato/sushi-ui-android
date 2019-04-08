package com.zomato.sushilib.templates.navigation

import android.content.Context
import android.database.DataSetObserver
import android.support.annotation.ColorInt
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiIconDrawable
import com.zomato.sushilib.atoms.textviews.SushiTextView


class SushiBottomNavigationBar : LinearLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var viewPager: ViewPager? = null
    private var tabViewDataProvider: TabViewDataProvider? = null

    private val selectedListeners = ArrayList<OnTabSelectedListener>()
    private val reselectedListeners = ArrayList<OnTabReselectedListener>()
    private var selectedPosition = -1

    private val datasetObserver: ViewPagerAdapterObserver by lazy { ViewPagerAdapterObserver() }

    init {
        orientation = LinearLayout.HORIZONTAL
    }

    fun setupWithViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        viewPager.adapter?.let { setupBottomTabViewManager(it) } ?: throw RuntimeException(
            "Adapter must be set on ViewPager before attaching it to ${SushiBottomNavigationBar::class.java}"
        )
        viewPager.addOnAdapterChangeListener(AdapterChangeListener())
        viewPager.addOnPageChangeListener(OnPageChangeListener())
        addOnTabSelectedListener(ViewPagerOnTabSelectedListener())
        viewPager.adapter?.registerDataSetObserver(datasetObserver)
        populateTabs()
    }

    fun setup(tabViewDataProvider: TabViewDataProvider) {
        this.tabViewDataProvider = tabViewDataProvider
        populateTabs()
    }

    fun addOnTabSelectedListener(listener: OnTabSelectedListener) {
        if (!selectedListeners.contains(listener)) {
            selectedListeners.add(listener)
        }
    }

    fun removeOnTabSelectedListener(listener: OnTabSelectedListener) {
        selectedListeners.remove(listener)
    }

    fun addOnTabReselectedListener(listener: OnTabReselectedListener) {
        if (!reselectedListeners.contains(listener)) {
            reselectedListeners.add(listener)
        }
    }

    fun removeOnTabReselectedListener(listener: OnTabReselectedListener) {
        reselectedListeners.remove(listener)
    }

    fun selectTab(position: Int) {
        //check if tab is reselected
        if (selectedPosition == position) {
            // dispatch tab reselected event
            dispatchTabReselectedEvent(position)
        } else {
            // un-select previous tab
            if (selectedPosition != -1) {
                getChildAt(selectedPosition)?.isSelected = false
            }

            // select tab at new position
            getChildAt(position)?.isSelected = true

            // update selected position
            selectedPosition = position

            // dispatch tab selected event
            dispatchTabSelectedEvent(position)
        }
    }

    private fun dispatchTabSelectedEvent(position: Int) {
        for (listener in selectedListeners) {
            listener.onTabSelected(position)
        }
    }

    private fun dispatchTabReselectedEvent(position: Int) {
        for (listener in reselectedListeners) {
            listener.onTabReselected(position)
        }
    }

    private fun setupBottomTabViewManager(pagerAdapter: PagerAdapter) {
        if (pagerAdapter is TabViewDataProvider) {
            this.tabViewDataProvider = pagerAdapter
        } else {
            throw RuntimeException("Adapter must implement " + TabViewDataProvider::class.java.name)
        }
    }

    private fun populateTabs() {
        removeAllViews()
        selectedPosition = -1

        tabViewDataProvider?.let { dataProvider ->
            val tabsCount = dataProvider.getCount()
            if (tabsCount == 0) return

            for (i in 0 until tabsCount) {
                addView(TabView(dataProvider.getTabData(i)))
            }
            viewPager?.currentItem?.takeIf { it < tabsCount }?.let {
                onTabSelected(it)
            } ?: onTabSelected(0)
        }
    }

    private fun onTabSelected(position: Int) {
        selectTab(position)
    }

    private inner class OnPageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            // no-op
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            // no-op
        }

        override fun onPageSelected(position: Int) {
            if (position != selectedPosition) {
                onTabSelected(position)
            }
        }

    }

    private inner class ViewPagerOnTabSelectedListener : OnTabSelectedListener {
        override fun onTabSelected(position: Int) {
            viewPager?.currentItem = position
        }
    }

    private inner class ViewPagerAdapterObserver : DataSetObserver() {
        override fun onChanged() {
            populateTabs()
        }

        override fun onInvalidated() {
            populateTabs()
        }
    }

    private inner class AdapterChangeListener internal constructor() : ViewPager.OnAdapterChangeListener {

        override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
            oldAdapter?.unregisterDataSetObserver(datasetObserver)
            newAdapter?.let {
                it.registerDataSetObserver(datasetObserver)
                setupBottomTabViewManager(it)
            }
            populateTabs()
        }
    }

    private inner class TabView(private val tabData: TabViewData) : LinearLayout(context) {

        private val textView: SushiTextView
        private val imageView: ImageView
        private val iconDrawable = SushiIconDrawable(context)

        init {
            gravity = Gravity.CENTER
            orientation = VERTICAL
            isClickable = true
            layoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f)

            View.inflate(context, R.layout.layout_bottom_navigation_bar_tab_view, this)
            textView = findViewById(R.id.text_view)
            imageView = findViewById(R.id.image_view)
            imageView?.setImageDrawable(iconDrawable)
            setData()
        }

        override fun performClick(): Boolean {
            super.performClick()
            onTabSelected(tabData.position)
            return true
        }

        override fun setSelected(selected: Boolean) {
            super.setSelected(selected)
            setData()
        }

        fun setData() {
            textView.text = tabData.title
            if (isSelected) {
                iconDrawable.editor().icon(tabData.activeIcon).colorInt(tabData.activeStateIconColor).apply()
                textView.setTextColor(tabData.activeStateTextColor)
            } else {
                iconDrawable.editor().icon(tabData.inactiveIcon).colorInt(tabData.inactiveStateIconColor).apply()
                textView.setTextColor(tabData.inactiveStateTextColor)
            }
        }
    }

    class TabViewData(
        val title: String,
        val activeIcon: String,
        val inactiveIcon: String,
        val position: Int,
        @ColorInt val activeStateTextColor: Int,
        @ColorInt val inactiveStateTextColor: Int,
        @ColorInt val activeStateIconColor: Int,
        @ColorInt val inactiveStateIconColor: Int
    )

    interface TabViewDataProvider {
        fun getCount(): Int
        fun getTabData(position: Int): TabViewData
    }

    interface OnTabSelectedListener {
        fun onTabSelected(position: Int)
    }

    interface OnTabReselectedListener {
        fun onTabReselected(position: Int)
    }
}
