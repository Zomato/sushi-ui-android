package com.zomato.sushiapp.fragments


import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.v4.app.Fragment
import android.support.v4.widget.TextViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zomato.sushiapp.R
import com.zomato.sushilib.annotations.ColorName
import com.zomato.sushilib.utils.color.ColorBuilder

/**
 * A simple [Fragment] subclass.
 *
 */
class ColorPaletteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Sample code to see how color builder works
        ColorBuilder(context!!, "red", 100).build()
        ColorBuilder(context!!).setColor(ColorName.BLUE).setTint(300).build()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_palette, container, false)
    }


}
