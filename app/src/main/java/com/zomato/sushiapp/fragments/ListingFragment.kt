package com.zomato.sushiapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R
import com.zomato.sushilib.molecules.listings.SushiImageTextListing

class ListingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_listing, container, false)
        rootView.findViewById<SushiImageTextListing>(R.id.sushi_image_text_listing)
            .imageView.setImageResource(R.drawable.ic_color_palette)
        return rootView
    }
}