package com.zomato.sushiapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zomato.sushiapp.R
import kotlinx.android.synthetic.main.fragment_image_views.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ImageViewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_image_views, container, false)

        rootView.cvGreen.layoutParams.height = 300
        rootView.civFoodPic.layoutParams.height = 200

        return rootView
    }


}
