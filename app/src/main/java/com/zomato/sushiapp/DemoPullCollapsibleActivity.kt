package com.zomato.sushiapp

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.widget.LinearLayout
import com.zomato.sushilib.organisms.stacks.page.SushiPullCollapsibleActivity

/**
 * Created by prempal on 2019-07-24.
 */
class DemoPullCollapsibleActivity : SushiPullCollapsibleActivity() {

    companion object {
        fun start(context: Context, expandFromRect: Rect) {
            val intent = Intent(context, DemoPullCollapsibleActivity::class.java)
            intent.putExtra(EXTRA_ENABLE_PULL_COLLAPSE, true)
            intent.putExtra(EXTRA_EXPAND_FROM_RECT, expandFromRect)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_pull_collapsible)
    }

}