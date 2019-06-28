package com.zomato.sushiapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO
import android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.zomato.sushilib.atoms.drawables.SushiIconDrawable
import com.zomato.sushilib.atoms.menu.SushiMenuItem
import com.zomato.sushilib.organisms.navigation.SushiBottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

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

        bottomNavigationView.setOnNavigationItemSelectedListener {
            val fragment = mainFragmentProvider.getItem(it.itemId)
            supportFragmentManager.beginTransaction().replace(R.id.container_main, fragment).commit()
            true
        }

        val menuList = arrayListOf<SushiMenuItem>()
        menuList.addAll(listOf(
            SushiMenuItem(itemId = 0, title = getString(R.string.sushi), drawable = getBottomNavDrawable()),
            SushiMenuItem(itemId = 1, title = getString(R.string.about), drawable = getBottomNavDrawable()),
            SushiMenuItem(itemId = 2, title = getString(R.string.zomato), drawable = getBottomNavDrawable())
        ))
        bottomNavigationView.itemIconTintList =
            ContextCompat.getColorStateList(this, R.color.bottom_nav_item_color_selector)
        bottomNavigationView.setMenu(menuList)
    }

    private fun getBottomNavDrawable(): Drawable {
        return SushiIconDrawable.Builder(container_main.context)
            .setIconChar(resources.getString(R.string.icon_filled_star))
            .build()
    }

}