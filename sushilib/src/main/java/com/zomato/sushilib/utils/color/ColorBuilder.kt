package com.zomato.sushilib.utils.color

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.ColorName
import com.zomato.sushilib.annotations.ColorTint

/**
 * created by championswimmer on 2019-05-08
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class ColorBuilder(val context: Context) {

    constructor(context: Context, @ColorName color: String, @ColorTint tint: Int) : this(context) {
        this.color = color
        this.tint = tint
    }
    @ColorName
    private var color = ColorName.RED

    @ColorTint
    private var tint = 500

    fun setColor(@ColorName color: String): ColorBuilder {
        this.color = color
        return this
    }

    fun setTint(@ColorTint tint: Int): ColorBuilder {
        this.tint = tint
        return this
    }

    @ColorRes
    private fun getColorResId(): Int {
        return when (color) {
            ColorName.RED -> when (tint) {
                100 -> R.color.sushi_red_100
                200 -> R.color.sushi_red_200
                300 -> R.color.sushi_red_300
                400 -> R.color.sushi_red_400
                500 -> R.color.sushi_red_500
                600 -> R.color.sushi_red_600
                700 -> R.color.sushi_red_700
                800 -> R.color.sushi_red_800
                900 -> R.color.sushi_red_900
                else -> R.color.sushi_red_500
            }
            ColorName.GREEN -> when (tint) {
                100 -> R.color.sushi_green_100
                200 -> R.color.sushi_green_200
                300 -> R.color.sushi_green_300
                400 -> R.color.sushi_green_400
                500 -> R.color.sushi_green_500
                600 -> R.color.sushi_green_600
                700 -> R.color.sushi_green_700
                800 -> R.color.sushi_green_800
                900 -> R.color.sushi_green_900
                else -> R.color.sushi_green_500
            }
            ColorName.BLUE -> when (tint) {
                50 -> R.color.sushi_blue_050
                100 -> R.color.sushi_blue_100
                200 -> R.color.sushi_blue_200
                300 -> R.color.sushi_blue_300
                400 -> R.color.sushi_blue_400
                500 -> R.color.sushi_blue_500
                600 -> R.color.sushi_blue_600
                700 -> R.color.sushi_blue_700
                800 -> R.color.sushi_blue_800
                900 -> R.color.sushi_blue_900
                else -> R.color.sushi_blue_500
            }
            ColorName.YELLOW -> when (tint) {
                50 -> R.color.sushi_yellow_050
                100 -> R.color.sushi_yellow_100
                200 -> R.color.sushi_yellow_200
                300 -> R.color.sushi_yellow_300
                400 -> R.color.sushi_yellow_400
                500 -> R.color.sushi_yellow_500
                600 -> R.color.sushi_yellow_600
                700 -> R.color.sushi_yellow_700
                800 -> R.color.sushi_yellow_800
                900 -> R.color.sushi_yellow_900
                else -> R.color.sushi_yellow_500
            }
            ColorName.GREY -> when (tint) {
                100 -> R.color.sushi_grey_100
                200 -> R.color.sushi_grey_200
                300 -> R.color.sushi_grey_300
                400 -> R.color.sushi_grey_400
                500 -> R.color.sushi_grey_500
                600 -> R.color.sushi_grey_600
                700 -> R.color.sushi_grey_700
                800 -> R.color.sushi_grey_800
                900 -> R.color.sushi_grey_900
                else -> R.color.sushi_grey_500
            }
            ColorName.PURPLE -> when (tint) {
                100 -> R.color.sushi_purple_100
                200 -> R.color.sushi_purple_200
                300 -> R.color.sushi_purple_300
                400 -> R.color.sushi_purple_400
                500 -> R.color.sushi_purple_500
                600 -> R.color.sushi_purple_600
                700 -> R.color.sushi_purple_700
                800 -> R.color.sushi_purple_800
                900 -> R.color.sushi_purple_900
                else -> R.color.sushi_purple_500
            }
            ColorName.LIME -> when (tint) {
                100 -> R.color.sushi_lime_100
                200 -> R.color.sushi_lime_200
                300 -> R.color.sushi_lime_300
                500 -> R.color.sushi_lime_500
                700 -> R.color.sushi_lime_700
                900 -> R.color.sushi_lime_900
                else -> R.color.sushi_lime_500
            }
            ColorName.INDIGO -> when (tint) {
                50 -> R.color.sushi_indigo_050
                100 -> R.color.sushi_indigo_100
                200 -> R.color.sushi_indigo_200
                300 -> R.color.sushi_indigo_300
                500 -> R.color.sushi_indigo_500
                700 -> R.color.sushi_indigo_700
                900 -> R.color.sushi_indigo_900
                else -> R.color.sushi_indigo_500
            }
            ColorName.CIDER -> when (tint) {
                50 -> R.color.sushi_cider_050
                100 -> R.color.sushi_cider_100
                200 -> R.color.sushi_cider_200
                300 -> R.color.sushi_cider_300
                500 -> R.color.sushi_cider_500
                700 -> R.color.sushi_cider_700
                900 -> R.color.sushi_cider_900
                else -> R.color.sushi_cider_500
            }
            ColorName.BROWN -> when (tint) {
                100 -> R.color.sushi_brown_100
                200 -> R.color.sushi_brown_200
                300 -> R.color.sushi_brown_300
                500 -> R.color.sushi_brown_500
                700 -> R.color.sushi_brown_700
                900 -> R.color.sushi_brown_900
                else -> R.color.sushi_brown_500
            }
            ColorName.TEAL -> when (tint) {
                50 -> R.color.sushi_teal_050
                100 -> R.color.sushi_teal_100
                200 -> R.color.sushi_teal_200
                300 -> R.color.sushi_teal_300
                500 -> R.color.sushi_teal_500
                700 -> R.color.sushi_teal_700
                900 -> R.color.sushi_teal_900
                else -> R.color.sushi_teal_500
            }
            ColorName.ORANGE -> when (tint) {
                100 -> R.color.sushi_orange_100
                200 -> R.color.sushi_orange_200
                300 -> R.color.sushi_orange_300
                500 -> R.color.sushi_orange_500
                700 -> R.color.sushi_orange_700
                900 -> R.color.sushi_orange_900
                else -> R.color.sushi_orange_500
            }
            ColorName.PINK -> when (tint) {
                100 -> R.color.sushi_pink_100
                200 -> R.color.sushi_pink_200
                300 -> R.color.sushi_pink_300
                500 -> R.color.sushi_pink_500
                700 -> R.color.sushi_pink_700
                900 -> R.color.sushi_pink_900
                else -> R.color.sushi_pink_500
            }
            ColorName.BLACK -> R.color.sushi_black
            ColorName.WHITE -> R.color.sushi_white
            else -> R.color.sushi_red_500
        }
    }

    @ColorInt
    fun build(): Int {
        return ContextCompat.getColor(context, getColorResId())
    }

}