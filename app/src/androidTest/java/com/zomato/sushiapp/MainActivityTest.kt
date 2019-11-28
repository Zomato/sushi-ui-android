package com.zomato.sushiapp

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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