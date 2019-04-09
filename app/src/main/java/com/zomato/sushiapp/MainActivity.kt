package com.zomato.sushiapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container_main,
                        mainFragmentProvider.getItem(position)
                    )
                    .commit()
            }
        })
        bottomNavigationBar.setup(mainFragmentProvider)
    }

}