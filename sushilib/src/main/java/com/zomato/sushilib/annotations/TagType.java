package com.zomato.sushilib.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.zomato.sushilib.annotations.TagType.*;

/**
 * created by championswimmer on 19/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({ROUNDED, CAPSULE, ROUNDED_OUTLINE, CAPSULE_OUTLINE, ROUNDED_DASHED, CAPSULE_DASHED})
public @interface TagType {
    int ROUNDED = 0;
    int CAPSULE = 1;
    int ROUNDED_OUTLINE = 2;
    int CAPSULE_OUTLINE = 3;
    int ROUNDED_DASHED = 4;
    int CAPSULE_DASHED = 5;
}
