package com.shubhank.covid_19.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.shubhank.covid_19.R
import com.shubhank.covid_19.fragment.GuidelinesFragment
import com.shubhank.covid_19.fragment.HomeFragment
import com.shubhank.covid_19.fragment.IsolationFragment


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var toolbar: Toolbar
    private lateinit var frameLayout: FrameLayout
    private lateinit var navigationView: NavigationView

    private var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationView)

        setUpToolbar()
        openDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when (it.itemId) {
                R.id.home -> {
                    openDashboard()
                    drawerLayout.closeDrawers()
                }
                R.id.guidelines -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            GuidelinesFragment()
                        )
                        .commit()

                    supportActionBar?.title = "Guidelines for Quarantine"
                    drawerLayout.closeDrawers()
                }
                R.id.isolation -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            IsolationFragment()
                        )
                        .commit()
                    supportActionBar?.title = "Things to do in Isolation"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    private fun openDashboard() {
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
        supportActionBar?.title = "Home"
        navigationView.setCheckedItem(R.id.home)
    }

    override fun onBackPressed() {

        when (supportFragmentManager.findFragmentById(R.id.frame)) {
            !is HomeFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }
}
