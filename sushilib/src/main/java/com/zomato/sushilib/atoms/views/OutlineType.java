package com.zomato.sushilib.atoms.views;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({OutlineType.CIRCLE, OutlineType.ROUNDED_RECT})
public @interface OutlineType {
    int CIRCLE = 0;
    int ROUNDED_RECT = 1;
}
