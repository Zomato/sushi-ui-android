package com.zomato.sushiapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R
import com.zomato.sushilib.molecule.listing.ZDualTextView
import com.zomato.sushilib.molecules.listings.SushiTwoLineListing
import kotlinx.android.synthetic.main.fragment_listing.view.*

class ListingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_listing, container, false)

        return rootView
    }
}