package com.zomato.sushiapp.fragments.buttons


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.zomato.sushiapp.R
import com.zomato.sushilib.annotations.ButtonType
import com.zomato.sushilib.atoms.buttons.SushiButton
import kotlinx.android.synthetic.main.fragment_buttons_solid.view.*

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
        val rootView =  when (mButtonStyle) {
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

        if (mButtonStyle == BUTTON_STYLE_SOLID) {

            rootView.btnDrawableSetter.setOnClickListener {
                (it as SushiButton).apply {

                    when (System.currentTimeMillis() % 3) {
                        0L -> setButtonType(ButtonType.TEXT)
                        1L -> setButtonType(ButtonType.OUTLINE)
                        2L -> setButtonType(ButtonType.SOLID)
                    }
                    when (System.currentTimeMillis() % 3) {
                        0L -> setButtonColor(ContextCompat.getColor(context, R.color.sushi_red_300))
                        1L -> setButtonColor(ContextCompat.getColor(context, R.color.sushi_green_400))
                        2L -> setButtonColor(ContextCompat.getColor(context, R.color.sushi_blue_500))
                    }

                    when (System.currentTimeMillis() % 8) {
                        0L -> setDrawableEnd(context.getDrawable(R.drawable.ic_circle_cross))
                        1L -> setDrawableStart(context.getDrawable(R.drawable.ic_color_palette))
                        2L -> setDrawableStart(context.getString(R.string.icon_filled_star))
                        3L -> setDrawableEnd(context.getString(R.string.icon_moon_empty))
                        4L -> setDrawableRight(context.getDrawable(R.drawable.ic_image_views))
                        5L -> setDrawableLeft(context.getDrawable(R.drawable.ic_search))
                        6L -> setDrawableRight(context.getString(R.string.icon_unfilled_star))
                        7L -> setDrawableLeft(context.getString(R.string.icon_right_triangle))
                    }
                }
            }
        }

        return rootView
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
