package com.zomato.sushiapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zomato.sushiapp.R
import com.zomato.sushilib.organisms.ratings.SushiRatingBar

class TagsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<SushiRatingBar>(R.id.rating_bar).setOnRatingChangeListener {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            true
        }

        view.findViewById<SushiRatingBar>(R.id.rating_bar2).setOnRatingChangeListener {
            Toast.makeText(context, "NOPE", Toast.LENGTH_SHORT).show()
            false
        }
    }
}