package com.zomato.sushiapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO
import android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.zomato.sushilib.atoms.menu.SushiMenuItem
import com.zomato.sushilib.templates.navigation.SushiBottomNavigationView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<SushiBottomNavigationView>(R.id.bottom_nav_bar)
        val mainFragmentProvider = MainFragmentProvider(this, supportFragmentManager)

        val menuList = arrayListOf<SushiMenuItem>()
        menuList.addAll(listOf(
            SushiMenuItem(itemId = R.id.menu_sushi, title = "Sushi", drawableId = R.drawable.ic_topnav_star, fragment = mainFragmentProvider.getItem(0)),
            SushiMenuItem(itemId = R.id.menu_zomato, title = "About", drawableId = R.drawable.ic_topnav_star, fragment = mainFragmentProvider.getItem(1)),
            SushiMenuItem(itemId = R.id.menu_about, title = "Zomato", drawableId = R.drawable.ic_topnav_star, fragment = mainFragmentProvider.getItem(2))
        ))

//
//        menuList.addAll(listOf(
//            SushiMenuItem(Menu.NONE, R.id.menu_sushi, Menu.NONE, "Sushi", R.drawable.ic_topnav_star, mainFragmentProvider.getItem(0)),
//            SushiMenuItem(Menu.NONE, R.id.menu_zomato, Menu.NONE, "About", R.drawable.ic_topnav_star, mainFragmentProvider.getItem(1)),
//            SushiMenuItem(Menu.NONE, R.id.menu_about, Menu.NONE, "Zomato", R.drawable.ic_topnav_star, mainFragmentProvider.getItem(2))
//        ))

        bottomNavigationView.setMenu(menuList, supportFragmentManager, R.id.container_main)
//
//        bottomNavigationView.menu.add(Menu.NONE, R.id.menu_sushi, Menu.NONE, "Sushi").setIcon(R.drawable.ic_topnav_star)
//        bottomNavigationView.menu.add(Menu.NONE, R.id.menu_zomato, Menu.NONE, "About").setIcon(R.drawable.ic_topnav_star)
//        bottomNavigationView.menu.add(Menu.NONE, R.id.menu_about, Menu.NONE, "Zomato").setIcon(R.drawable.ic_topnav_star)

//        bottomNavigationView.setOnNavigationItemSelectedListener{ menuItem ->
//            val pos = when {
//                menuItem.itemId == R.id.menu_sushi -> 0
//                menuItem.itemId == R.id.menu_zomato -> 1
//                else -> 2
//            }
//            val tag = mainFragmentProvider.getTitle(pos)
//            val fragment = mainFragmentProvider.getItem(pos)
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container_main, fragment, tag)
//                .commit()
//            true
//        }
//
//        bottomNavigationView.setStartDestination(R.id.menu_sushi)

        //bottomNavigationView.selectedItemId = R.id.menu_sushi


//        bottomNavigationBar.addOnTabSelectedListener(obdject :
//            SushiBottomNavigationBar.OnTabSelectedListener {
//            override fun onTabSelected(position: Int) {
//                val tag = mainFragmentProvider.getTitle(position)
//                val fragment = mainFragmentProvider.getItem(position)
//
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.container_main, fragment, tag)
//                    .commit()
//            }
//        })
//        bottomNavigationBar.setup(mainFragmentProvider)
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