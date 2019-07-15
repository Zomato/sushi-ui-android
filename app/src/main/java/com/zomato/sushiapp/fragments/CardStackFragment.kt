package com.zomato.sushiapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zomato.sushiapp.R
import com.zomato.sushilib.molecules.listings.SushiTextListing
import com.zomato.sushilib.organisms.stacks.CollapsingCardStack
import kotlinx.android.synthetic.main.fragment_card_stack.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CardStackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_stack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rvSample.layoutManager = LinearLayoutManager(view.context)
        view.rvSample.adapter = object : ListAdapter<Any, DummyVH>(
            object : DiffUtil.ItemCallback<Any>() {
                override fun areItemsTheSame(p0: Any, p1: Any) = false
                override fun areContentsTheSame(p0: Any, p1: Any) = false
            }
        ) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyVH {
                if (viewType == 1) {

                    return DummyVH(SushiTextListing(parent.context).apply {
                        headlineText = "OMG WTF"
                        subtitleText = "This is shit"
                    })

                } else {
                    return DummyVH(CollapsingCardStack(parent.context).apply {
                        repeat(5) {

                            addView(SushiTextListing(parent.context).apply {
                                headlineText = "OMG $it IS AWESOME"
                                subtitleText = "Can't believe it"
                                setBackgroundColor(
                                    when (it) {
                                        0 -> ContextCompat.getColor(context, R.color.sushi_blue_100)
                                        1 -> ContextCompat.getColor(context, R.color.sushi_red_100)
                                        2 -> ContextCompat.getColor(context, R.color.sushi_green_100)
                                        3 -> ContextCompat.getColor(context, R.color.sushi_brown_100)
                                        else -> ContextCompat.getColor(context, R.color.sushi_indigo_100)
                                    }
                                )
                            })
                        }

                    })
                }

            }

            override fun getItemViewType(position: Int): Int {
                return if (position == 5) {
                    0
                } else {
                    1
                }
            }

            override fun onBindViewHolder(viewHolder: DummyVH, pos: Int) {
                // NADA
            }

            override fun getItemCount(): Int = 30

        }
    }

    inner class DummyVH(itemView: View) : RecyclerView.ViewHolder(itemView)


}
