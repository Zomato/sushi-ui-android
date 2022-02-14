package com.zomato.sushiapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.zomato.sushiapp.R
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
            setExpandedTitleTypeface(ResourcesCompat.getFont(context, R.font.okra_light))
            setCollapsedTitleTypeface(ResourcesCompat.getFont(context, R.font.okra_light))
        }
    }


}
