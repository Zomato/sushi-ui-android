package com.zomato.sushiapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.zomato.sushiapp.fragments.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.view.WindowManager
import android.os.Build
import com.zomato.sushilib.utils.text.TextFormatUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        collapsible_toolbar.apply {
            setExpandedTitleTypeface(
                TextFormatUtils.sushiFontWeightToTypeface(
                    context,
                    600
                )
            )

            setCollapsedTitleTypeface(
                TextFormatUtils.sushiFontWeightToTypeface(
                    context,
                    600
                )
            )
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, HomeFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }


}
