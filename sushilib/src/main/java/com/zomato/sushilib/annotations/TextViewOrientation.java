package com.zomato.sushilib.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by prempal on 2019-05-21.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({TextViewOrientation.HORIZONTAL, TextViewOrientation.VERTICAL})
public @interface TextViewOrientation {
    int HORIZONTAL = 0;
    int VERTICAL = 1;
}
