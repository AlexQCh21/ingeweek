package com.cursokotlin.ingeweek

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        showHomeFragment()
        clickItemMenuNav()
    }

    fun showHomeFragment(){
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, homeFragment)
            .addToBackStack(null)
            .commit()
    }

    fun showHomeFragmentEvents(){
        val eventsFragment = EventsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, eventsFragment)
            .addToBackStack(null)
            .commit()
    }

    fun showHomeFragmentUni(){
        val universidadFragment = UniversidadFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, universidadFragment)
            .addToBackStack(null)
            .commit()
    }


    fun clickItemMenuNav(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showHomeFragment()
                    true
                }
                R.id.nav_search -> {
                    showHomeFragmentEvents()
                    true
                }
                R.id.nav_profile -> {
                    showHomeFragmentUni()
                    true
                }
                else -> false
            }
        }
    }
}