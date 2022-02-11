package com.zomato.sushiapp

import android.text.TextUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

import org.junit.Assert.*


/**
 * created by championswimmer on 2019-05-10
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@RunWith(RobolectricTestRunner::class)
class MainFragmentProviderTest {

    @JvmField
    var mainActivity = Robolectric.setupActivity(MainActivity::class.java)

    @JvmField
    var mainFragmentProvider = MainFragmentProvider(
        RuntimeEnvironment.application,
        mainActivity.supportFragmentManager
    )


    @Test
    fun `getCount returns 3`() {
        assertEquals(3, mainFragmentProvider.getCount())
    }

    @Test
    fun `getTitle zomato_at 1`() {
        assertEquals("Zomato", mainFragmentProvider.getTitle(1))
    }

    @Test
    fun `getTabData icons not empty`() {
        val tabData = mainFragmentProvider.getTabData(1)
        assertFalse(TextUtils.isEmpty(tabData.activeIcon))
        assertFalse(TextUtils.isEmpty(tabData.inactiveIcon))

    }


}