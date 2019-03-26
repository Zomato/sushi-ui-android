package com.zomato.sushilib.atoms.views;

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
public interface Edge {
    int LEFT = 1 << 3;  // 1000
    int TOP = 1 << 2;   // 0100
    int RIGHT = 1 << 1; // 0010
    int BOTTOM = 1;     // 0001
}
