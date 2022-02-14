package com.zomato.sushiapp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        ColorBuilder(requireContext(), ColorName.RED, 100).build()
        ColorBuilder(requireContext()).setColor(ColorName.BLUE).setTint(300).build()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_palette, container, false)
    }


}
