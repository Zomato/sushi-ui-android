package com.zomato.sushiapp

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * created by championswimmer on 2019-05-10
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@RunWith(AndroidJUnit4::class)
class ComponentActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(ComponentActivity::class.java, false, false)

    @JvmField
    val appContext = InstrumentationRegistry.getTargetContext()


    fun getComponentIntent(componentName: Int) =
        Intent(appContext, ComponentActivity::class.java).apply {
            putExtra("type", componentName)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            appContext.startActivity(this)
        }

    @Test
    fun ComponentActivity_launch_COLORS() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.COLORS)))
    }

    @Test
    fun ComponentActivity_launch_TYPOGRAPHY() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.TYPOGRAPHY)))
    }

    @Test
    fun ComponentActivity_launch_IMAGES() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.IMAGES)))
    }

    @Test
    fun ComponentActivity_launch_ICONS() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.ICONS)))
    }

    @Test
    fun ComponentActivity_launch_BUTTONS() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.BUTTONS)))
    }

    @Test
    fun ComponentActivity_launch_FORM() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.FORM)))
    }

    @Test
    fun ComponentActivity_launch_SNIPPETS() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.SNIPPETS)))
    }

    @Test
    fun ComponentActivity_launch_TAGS() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.TAGS)))
    }

    @Test
    fun ComponentActivity_launch_MENU_TABS() {
        assertNotNull(activityTestRule.launchActivity(getComponentIntent(ComponentActivity.MENU_TABS)))
    }
}