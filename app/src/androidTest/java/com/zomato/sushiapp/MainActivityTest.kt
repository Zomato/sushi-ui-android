package com.zomato.sushiapp

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * created by championswimmer on 2019-05-10
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun MainActivity_launches() {
        assertNotNull(activityTestRule.activity)
    }
}