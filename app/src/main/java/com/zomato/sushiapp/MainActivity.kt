package com.zomato.sushiapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO
import android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES
import android.view.Menu
import android.view.MenuItem
import com.zomato.sushilib.templates.navigation.SushiBottomNavigationBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationBar = findViewById<SushiBottomNavigationBar>(R.id.bottom_nav_bar)
        val mainFragmentProvider = MainFragmentProvider(this, supportFragmentManager)

        bottomNavigationBar.addOnTabSelectedListener(object :
            SushiBottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                val tag = mainFragmentProvider.getTitle(position)
                val fragment = mainFragmentProvider.getItem(position)

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, fragment, tag)
                    .commit()
            }
        })
        bottomNavigationBar.setup(mainFragmentProvider)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_nightmode) {
            toggleNightMode()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun toggleNightMode() {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        if (currentMode != MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        } else if (currentMode != MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
        recreate()
    }

}