package com.zomato.sushilib.atoms.views;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.zomato.sushilib.atoms.views.Edge.*;

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        Corner.TOP_LEFT, Corner.TOP_RIGHT,
        Corner.BOTTOM_LEFT, Corner.BOTTOM_RIGHT
})
public @interface Corner {

    int TOP_LEFT = TOP | LEFT;                      // 1100
    int TOP_RIGHT = TOP | RIGHT;                    // 0110
    int BOTTOM_LEFT = BOTTOM | LEFT;                // 0011
    int BOTTOM_RIGHT = BOTTOM | RIGHT;              // 1001

    int ALL_CORNERS = TOP | LEFT | BOTTOM | RIGHT;  // 1111
    int NO_CORNERS = ~ALL_CORNERS;                  // 0000
}
