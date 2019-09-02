package com.zomato.sushiapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.zomato.sushiapp.R
import com.zomato.sushilib.annotations.TagType
import com.zomato.sushilib.molecules.tags.SushiTag
import com.zomato.sushilib.organisms.ratings.SushiRatingBar
import kotlinx.android.synthetic.main.fragment_tag.view.*

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

        view.tagClicker.setOnClickListener {
            (it as SushiTag).apply {
                when (System.currentTimeMillis() % 6) {
                    0L -> tagType = TagType.CAPSULE
                    1L -> tagType = TagType.ROUNDED
                    2L -> tagType = TagType.CAPSULE_OUTLINE
                    3L -> tagType = TagType.ROUNDED_OUTLINE
                    4L -> tagType = TagType.CAPSULE_DASHED
                    5L -> tagType = TagType.ROUNDED_DASHED
                }

                when (System.currentTimeMillis() % 3) {
                    0L -> tagColor = ContextCompat.getColor(context, R.color.sushi_pink_500)
                    1L -> tagColor = ContextCompat.getColor(context, R.color.sushi_indigo_300)
                    2L -> tagColor = ContextCompat.getColor(context, R.color.sushi_yellow_800)
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

                when (System.currentTimeMillis() % 2) {
                    0L -> {
                        text = "Click to Change"
                        compoundDrawablePadding = resources.getDimensionPixelSize(R.dimen.sushi_spacing_mini)
                    }
                    1L -> {
                        text = ""
                        compoundDrawablePadding = 0
                    }
                }
            }
        }
    }
}