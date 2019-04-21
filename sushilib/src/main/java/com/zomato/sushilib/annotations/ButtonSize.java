package com.zomato.sushilib.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.zomato.sushilib.annotations.ButtonSize.*;

/**
 * created by championswimmer on 19/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({LARGE, MEDIUM, SMALL})
public @interface ButtonSize {
    int LARGE = 0;
    int MEDIUM = 1;
    int SMALL = 2;
}
