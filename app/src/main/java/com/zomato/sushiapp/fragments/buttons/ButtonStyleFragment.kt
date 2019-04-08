package com.zomato.sushiapp.fragments.buttons


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R

/**
 * A simple [Fragment] subclass.
 * Created by championswimmer. Copyright (c) Zomato, 2019
 *
 */
class ButtonStyleFragment : Fragment() {

    private var mButtonStyle = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mButtonStyle = it.getInt(ARG_BUTTON_STYLE, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (mButtonStyle) {
            BUTTON_STYLE_SOLID -> inflater.inflate(
                R.layout.fragment_buttons_solid,
                container,
                false
            )
            BUTTON_STYLE_OUTLINE -> inflater.inflate(
                R.layout.fragment_buttons_outline,
                container,
                false
            )
            BUTTON_STYLE_TEXT -> inflater.inflate(
                R.layout.fragment_buttons_text,
                container,
                false
            )
            else -> inflater.inflate(
                R.layout.fragment_buttons_solid,
                container,
                false
            )
        }
    }

    companion object {
        private const val ARG_BUTTON_STYLE = "button_style"

        const val BUTTON_STYLE_SOLID = 0
        const val BUTTON_STYLE_OUTLINE = 1
        const val BUTTON_STYLE_TEXT = 2

        fun getTitle(pos: Int) = when (pos) {
            BUTTON_STYLE_SOLID -> "Solid"
            BUTTON_STYLE_OUTLINE -> "Outline"
            BUTTON_STYLE_TEXT -> "Text"
            else -> "Solid"
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param buttonStyle
         * @return A new instance of [ButtonStyleFragment]
         */
        @JvmStatic
        fun newInstance(buttonStyle: Int) =
            ButtonStyleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_BUTTON_STYLE, buttonStyle)
                }
            }

    }


}
