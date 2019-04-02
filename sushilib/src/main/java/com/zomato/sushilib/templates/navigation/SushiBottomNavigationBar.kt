package com.zomato.sushilib.templates.navigation

import android.content.Context
import android.database.DataSetObserver
import android.support.annotation.ColorInt
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiIconDrawable


class SushiBottomNavigationBar : LinearLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var viewPager: ViewPager? = null
    private var adapter: PagerAdapter? = null

    private var bottomTabViewManager: BottomTabViewManager? = null
    private val selectedListeners = ArrayList<OnTabSelectedListener>()
    private val reselectedListeners = ArrayList<OnTabReselectedListener>()
    private var selectedPosition = -1

    init {
        orientation = LinearLayout.HORIZONTAL
    }

    fun setupWithViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        if (viewPager.adapter == null) {
            throw RuntimeException("Adapter must be set on ViewPager before attaching it to  ${SushiBottomNavigationBar::class.java}")
        }
        viewPager.adapter?.let {
            this.adapter = it
//            if (it is BottomTabViewManager) {
//
//                this.bottomTabViewManager = it
//            } else {
//                throw RuntimeException("Adapter must implement " + BottomTabViewManager::class.java.name)
//            }
        }
        viewPager.addOnAdapterChangeListener(AdapterChangeListener())
        viewPager.addOnPageChangeListener(OnPageChangeListener())
        addOnTabSelectedListener(ViewPagerOnTabSelectedListener())
        adapter?.registerDataSetObserver(ViewPagerAdapterObserver())
        populateFromPagerAdapter()
    }

    fun setData(list: List<TabViewData>?) {
        bottomTabViewManager = BottomTabViewManagerImpl(list)
        populateFromPagerAdapter()
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

    // todo rename
    private fun populateFromPagerAdapter() {
        removeAllViews()
        selectedPosition = -1

        val adapterCount = bottomTabViewManager?.getCount()
        if (adapterCount == null || adapterCount == 0) return

        for (i in 0 until adapterCount) {
            addView(bottomTabViewManager?.createBottomTabView(this, i))
        }
        viewPager?.currentItem?.takeIf { it < adapterCount }?.let {
            onTabSelected(it)
        }
    }

    private fun onTabSelected(position: Int, tabId: String? = "") {
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
        override fun onTabSelected(position: Int, tabId: String?) {
            viewPager?.currentItem = position
        }
    }

    private inner class ViewPagerAdapterObserver : DataSetObserver() {
        override fun onChanged() {
            populateFromPagerAdapter()
        }

        override fun onInvalidated() {
            populateFromPagerAdapter()
        }
    }

    private inner class AdapterChangeListener internal constructor() : ViewPager.OnAdapterChangeListener {

        override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
            adapter = newAdapter
//            tabDataProvider = newAdapter
            // todo check if invalidation required
        }
    }

    private inner class TabView : LinearLayout(context) {

        val textView: TextView
        val imageView: ImageView

        private var tabData: TabViewData? = null
        private var position: Int = -1
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

        fun setTabData(data: TabViewData, position: Int) {
            tabData = data
            this.position = position
            setData()
        }

        override fun performClick(): Boolean {
            super.performClick()
//            tabClickListener?.onTabClicked(tabData.position, tabData.tabId, tabData.trackId)
            onTabSelected(position, tabData?.tabId)
            return true
        }

        override fun setSelected(selected: Boolean) {
            super.setSelected(selected)
            setData()
        }

        fun setData() {
            tabData?.let { tabData ->
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
    }

    class TabViewData(
        val title: String,
        val activeIcon: String,
        val inactiveIcon: String,
        @ColorInt val activeStateTextColor: Int,
        @ColorInt val inactiveStateTextColor: Int,
        @ColorInt val activeStateIconColor: Int,
        @ColorInt val inactiveStateIconColor: Int, val tabId: String? = ""
    )


    interface BottomTabViewManager {
        fun getCount(): Int
        fun createBottomTabView(parent: ViewGroup, position: Int): View
        // todo check if necessary
//        fun bindDataToView(view: View, position: Int)
    }

    interface OnTabSelectedListener {
        fun onTabSelected(position: Int, tabId: String? = "")
    }

    interface OnTabReselectedListener {
        fun onTabReselected(position: Int, tabId: String? = "")
    }

    private inner class BottomTabViewManagerImpl(private val tabViewDataList: List<TabViewData>?) : BottomTabViewManager {
        override fun getCount(): Int = tabViewDataList?.size ?: 0

        override fun createBottomTabView(parent: ViewGroup, position: Int): View = TabView().apply {
            tabViewDataList?.get(position)?.let {
                setTabData(it, position)
            }

        }

//        override fun bindDataToView(view: View, position: Int) {
//            tabViewDataList?.get(position)?.let {
//                (view as? TabView)?.setTabData(it,position)
//            }
//
//        }

    }

}
