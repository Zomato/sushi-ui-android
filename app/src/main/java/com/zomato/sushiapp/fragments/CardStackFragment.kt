package com.zomato.sushiapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.zomato.sushiapp.R
import com.zomato.sushiapp.viewimpls.DemoCollapsingCardStack
import com.zomato.sushilib.organisms.stacks.SushiCollapsingCardStack
import com.zomato.sushilib.utils.dimens.DimenUtils.dp2px
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

        val cardStackAdapter = object : SushiCollapsingCardStack.Adapter {
            override fun getItemCount(): Int = 5

            override fun getView(parent: ViewGroup, position: Int): View {
                val cardView = (layoutInflater.inflate(
                    R.layout.list_item_stackable_card,
                    parent,
                    false
                ) as CardView)

                cardView.apply {
                    cardElevation = position * dp2px(context, 4f)
                    radius = dp2px(context, 6f)

                    setBackgroundColor(
                        when (position) {
                            0 -> ContextCompat.getColor(context, R.color.sushi_blue_100)
                            1 -> ContextCompat.getColor(context, R.color.sushi_red_100)
                            2 -> ContextCompat.getColor(context, R.color.sushi_green_100)
                            3 -> ContextCompat.getColor(context, R.color.sushi_yellow_200)
                            else -> ContextCompat.getColor(context, R.color.sushi_indigo_100)
                        }
                    )
                }
                return cardView
            }

        }
        view.rvSample.layoutManager = LinearLayoutManager(view.context)
        view.rvSample.adapter = object : ListAdapter<Any, DummyVH>(
            object : DiffUtil.ItemCallback<Any>() {
                override fun areItemsTheSame(p0: Any, p1: Any) = false
                override fun areContentsTheSame(p0: Any, p1: Any) = false
            }
        ) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyVH {
                if (viewType == 1) {

                    return DummyVH(
                        layoutInflater.inflate(
                            R.layout.list_item_dummy_about,
                            parent,
                            false
                        )
                    )

                } else {
                    return DummyVH(DemoCollapsingCardStack(parent.context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        setAdapter(cardStackAdapter)
                        setExpandablePage(view.expandable_page)
                    })
                }

            }

            override fun getItemViewType(position: Int): Int =
                when (position) {
                    1 -> 0
                    else -> 1
                }

            override fun onBindViewHolder(viewHolder: DummyVH, pos: Int) {
                // NADA
            }

            override fun getItemCount(): Int = 5

        }
    }

    inner class DummyVH(itemView: View) : RecyclerView.ViewHolder(itemView)


}
