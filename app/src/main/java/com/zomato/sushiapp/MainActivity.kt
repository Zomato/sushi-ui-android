package com.zomato.sushiapp

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO
import android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.zomato.sushilib.atoms.menu.SushiMenuItem
import com.zomato.sushilib.organisms.navigation.SushiBottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
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

    private fun toggleNightMode() {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        if (currentMode != MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        } else if (currentMode != MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
        recreate()
    }

    private fun setupBottomNavigation() {
        val view = findViewById<CoordinatorLayout>(R.id.container_main)
        val params = CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.BOTTOM

        val bottomNavigationView = SushiBottomNavigationView(this)
        bottomNavigationView.elevation = resources.getDimension(R.dimen.sushi_spacing_mini)
        view.addView(bottomNavigationView, params)

        val mainFragmentProvider = MainFragmentProvider(this, supportFragmentManager)

        val menuList = arrayListOf<SushiMenuItem>()
        menuList.addAll(listOf(
            SushiMenuItem(itemId = 0, title = getString(R.string.sushi), drawableId = R.drawable.ic_topnav_star, fragment = mainFragmentProvider.getItem(0)),
            SushiMenuItem(itemId = 1, title = getString(R.string.about), drawableId = R.drawable.ic_topnav_star, fragment = mainFragmentProvider.getItem(1)),
            SushiMenuItem(itemId = 2, title = getString(R.string.zomato), drawableId = R.drawable.ic_topnav_star, fragment = mainFragmentProvider.getItem(2))
        ))
        bottomNavigationView.setMenu(menuList, supportFragmentManager, R.id.container_main)
    }

}