package com.zomato.sushilib.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by prempal on 2019-05-21.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({CheckableSelectorType.RADIO, CheckableSelectorType.CHECKBOX})
public @interface CheckableSelectorType {
    int RADIO = 0;
    int CHECKBOX = 1;
}
