package com.zomato.sushilib.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.zomato.sushilib.annotations.FontWeight.*;

/**
 * created by championswimmer on 19/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({THIN, EXTRALIGHT, LIGHT, REGULAR, MEDIUM, SEMIBOLD})
public @interface FontWeight {
    int THIN = 100;
    int EXTRALIGHT = 200;
    int LIGHT = 300;
    int REGULAR = 400;
    int MEDIUM = 500;
    int SEMIBOLD = 600;
}
