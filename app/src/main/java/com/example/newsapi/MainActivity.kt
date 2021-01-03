package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatViewInflater
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*

lateinit var drawerLayout: DrawerLayout
lateinit var navView: NavigationView
lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)


        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)


        actionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                drawerView.id
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

        }
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.politics -> {
                     navToFragment("Politics")
                    true
                }
                R.id.sport -> {
                     navToFragment( "Sport")
                    true
                }
                R.id.economic -> {
                     navToFragment("Economic")
                    true
                }
                else -> {
                    false
                }
            }
        }


        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, NewsFragment.newNewsInstance())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    fun  navToFragment(title: String) {
        val fragment = NewsFragment.newInstance(title)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        toolbar.setTitle(title)
        drawerLayout.closeDrawer(GravityCompat.START)
    }




}