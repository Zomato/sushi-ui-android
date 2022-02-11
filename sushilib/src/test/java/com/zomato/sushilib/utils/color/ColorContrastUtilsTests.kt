package com.zomato.sushilib.utils.color

import android.graphics.Color
import org.junit.Test
import org.junit.Assert.*

class ColorContrastUtilsTests {
    @Test
    fun checkIsDarkWorks() {
        assertTrue(ColorContrastUtils.isDarkColor(Color.RED))
        assertFalse(ColorContrastUtils.isDarkColor(Color.YELLOW))
    }
}