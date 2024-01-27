package com.example.notes.navigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.example.notes.note.BaseActivity
import com.example.notes.R
import com.google.android.material.navigation.NavigationView

class NavDraweractivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController
    override fun initComponents() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_nav_drawer
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_label -> {
                navigateTo(R.id.nav_label)
            }
            R.id.nav_reminders -> {
                navigateTo(R.id.nav_reminders)
            }
            R.id.nav_notes -> {
                navigateTo(R.id.nav_notes)
            }
            R.id.nav_archive -> {
                navigateTo(R.id.nav_archive)
            }
            R.id.nav_deleted -> {
                navigateTo(R.id.nav_deleted)
            }
            R.id.nav_help -> {
                navigateTo(R.id.nav_help)
            }
            R.id.nav_settings -> {
                navigateTo(R.id.nav_settings)
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    fun navigateTo(id: Int) {
        Navigation.findNavController(this, R.id.fragment).navigate(id)
    }

    fun navigateTo(id: Int, bundle: Bundle) {
        Navigation.findNavController(this, R.id.fragment).navigate(id, bundle)
    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}