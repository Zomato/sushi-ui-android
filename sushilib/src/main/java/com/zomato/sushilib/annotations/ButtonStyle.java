package com.zomato.sushilib.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.zomato.sushilib.annotations.ButtonStyle.*;

/**
 * created by championswimmer on 19/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({SOLID, OUTLINE, TEXT})
public @interface ButtonStyle {
    int SOLID = 0;
    int OUTLINE = 1;
    int TEXT = 2;
}
