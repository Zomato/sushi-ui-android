package com.zomato.sushilib.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.zomato.sushilib.annotations.TagSize.*;

/**
 * created by championswimmer on 19/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({LARGE, MEDIUM, SMALL, TINY})
public @interface TagSize {
    int LARGE = 0;
    int MEDIUM = 1;
    int SMALL = 2;
    int TINY = 3;
}
