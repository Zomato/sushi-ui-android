package com.zomato.sushiapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R
import com.zomato.sushilib.utils.text.TextFormatUtils
import kotlinx.android.synthetic.main.fragment_main.*

class AboutSushiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_sushi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = "About"

        collapsible_toolbar.apply {
            setExpandedTitleTypeface(
                TextFormatUtils.sushiFontWeightToTypeface(
                    context,
                    600
                )
            )

            setCollapsedTitleTypeface(
                TextFormatUtils.sushiFontWeightToTypeface(
                    context,
                    600
                )
            )
        }
    }


}
