package com.zomato.sushilib.annotations;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.zomato.sushilib.annotations.ColorName.*;

/**
 * created by championswimmer on 2019-05-09
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({
        BLACK, WHITE,
        RED, GREEN, BLUE, GREY, YELLOW, PURPLE,
        INDIGO, TEAL, ORANGE, BROWN, PINK
})
public @interface ColorName {
    // Constant Colors
    String BLACK = "black";
    String WHITE = "white";

    // Primary Colors
    String RED = "red";
    String GREEN = "green";
    String BLUE = "blue";
    String GREY = "grey";
    String YELLOW = "yellow";
    String PURPLE = "purple";

    // Secondary Colors
    String INDIGO = "indigo";
    String TEAL = "teal";
    String ORANGE = "orange";
    String BROWN = "brown";
    String PINK = "pink";
}
