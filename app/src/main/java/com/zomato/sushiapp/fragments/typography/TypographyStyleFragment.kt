package com.zomato.sushiapp.fragments.typography


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [TypographyStyleFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TypographyStyleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mFontWeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFontWeight = it.getInt(ARG_FONT_WEIGHT, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return when(mFontWeight) {
            FONT_WEIGHT_EXTRALIGHT -> inflater.inflate(
                R.layout.fragment_typography_style_extralight,
                container,
                false
            )
            FONT_WEIGHT_LIGHT -> inflater.inflate(
                R.layout.fragment_typography_style_light,
                container,
                false
            )
            FONT_WEIGHT_REGULAR -> inflater.inflate(
                R.layout.fragment_typography_style_regular,
                container,
                false
            )
            FONT_WEIGHT_MEDIUM -> inflater.inflate(
                R.layout.fragment_typography_style_medium,
                container,
                false
            )
            FONT_WEIGHT_SEMIBOLD -> inflater.inflate(
                R.layout.fragment_typography_style_semibold,
                container,
                false
            )
            else -> inflater.inflate(
                R.layout.fragment_typography_style_regular,
                container,
                false
            )
        }
    }


    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_FONT_WEIGHT = "font_weight"

        const val FONT_WEIGHT_EXTRALIGHT = 0
        const val FONT_WEIGHT_LIGHT = 1
        const val FONT_WEIGHT_REGULAR = 2
        const val FONT_WEIGHT_MEDIUM = 3
        const val FONT_WEIGHT_SEMIBOLD = 4

        fun getTitle(pos: Int) = when (pos) {
            FONT_WEIGHT_EXTRALIGHT -> "ExtraLight"
            FONT_WEIGHT_LIGHT -> "Light"
            FONT_WEIGHT_REGULAR -> "Regular"
            FONT_WEIGHT_MEDIUM -> "Medium"
            FONT_WEIGHT_SEMIBOLD -> "SemiBold"
            else -> "Regular"
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param fontWeight Parameter 1.
         * @return A new instance of fragment TypographyStyleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(fontWeight: Int) =
            TypographyStyleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_FONT_WEIGHT, fontWeight)
                }
            }
    }
}
