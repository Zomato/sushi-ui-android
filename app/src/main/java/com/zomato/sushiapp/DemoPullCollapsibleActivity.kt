package com.zomato.sushiapp

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.widget.LinearLayout
import com.zomato.sushilib.organisms.stacks.page.PullCollapsibleActivity

/**
 * Created by prempal on 2019-07-24.
 */
class DemoPullCollapsibleActivity : PullCollapsibleActivity() {

    companion object {
        fun start(context: Context, expandFromRect: Rect) {
            val intent = Intent(context, DemoPullCollapsibleActivity::class.java)
            intent.putExtra(EXTRA_EXPAND_FROM_RECT, expandFromRect)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            setBackgroundColor(resources.getColor(R.color.sushi_yellow_100))
        }
        setContentView(layout)
        expandFrom(intent.getParcelableExtra(EXTRA_EXPAND_FROM_RECT))
    }

}