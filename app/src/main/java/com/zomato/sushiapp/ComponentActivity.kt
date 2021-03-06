package com.zomato.sushiapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zomato.sushiapp.fragments.*
import com.zomato.sushiapp.fragments.buttons.ButtonsMainFragment
import com.zomato.sushiapp.fragments.images.ImagesMainFragment
import com.zomato.sushiapp.fragments.typography.TypographyMainFragment
import kotlinx.android.synthetic.main.app_bar_main.*

class ComponentActivity : AppCompatActivity() {

    companion object {
        const val COLORS = 0
        const val TYPOGRAPHY = 1
        const val IMAGES = 2
        const val ICONS = 3
        const val BUTTONS = 4
        const val FORM = 5
        const val SNIPPETS = 6
        const val TAGS = 7
        const val MENU_TABS = 8
        const val CARD_STACK = 9

        fun start(context: Context, component: Int) {
            Intent(context, ComponentActivity::class.java).apply {
                putExtra("type", component)
                context.startActivity(this)
            }

        }

        private fun getFragment(component: Int): Fragment = when (component) {
            COLORS -> ColorPaletteFragment()
            TYPOGRAPHY -> TypographyMainFragment()
            IMAGES -> ImagesMainFragment()
            ICONS -> ColorPaletteFragment()
            BUTTONS -> ButtonsMainFragment()
            FORM -> FormComponentFragment()
            SNIPPETS -> ListingFragment()
            TAGS -> TagsFragment()
            MENU_TABS -> NavigationComponentsFragment()
            CARD_STACK -> CardStackFragment()
            else -> ColorPaletteFragment()
        }

        private fun getPageTitle(component: Int): String = when (component) {
            COLORS -> "Color palette"
            TYPOGRAPHY -> "Typography"
            ICONS -> "Icon palette"
            BUTTONS -> "Buttons"
            FORM -> "Form Components"
            SNIPPETS -> "Listing snippets"
            TAGS -> "Tags and rating"
            MENU_TABS -> "Navigation components"
            CARD_STACK -> "Card Stack"
            else -> "Color palette"
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)
        val type = intent?.getIntExtra("type", COLORS) ?: COLORS
        val fragment = getFragment(type)
        setupActionBar(type)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, fragment)
            .commit()
    }

    private fun setupActionBar(type: Int) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getPageTitle(type)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}