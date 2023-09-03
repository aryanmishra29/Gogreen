package com.example.gogreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

//@Suppress("DEPRECATION")
class UsersActivity : AppCompatActivity() {
//    private val homeFragment = HomeFragment()
//    private val cartFragment = CartFragment()
//    private val profileFragment = ProfileFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_activity)

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//
//        // Set the HomeFragment as the initial fragment
//        setCurrentFragment(homeFragment)
//
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_home -> setCurrentFragment(homeFragment)
//                R.id.nav_cart -> setCurrentFragment(cartFragment)
//                R.id.nav_profile -> setCurrentFragment(profileFragment)
//            }
//            true
//        }
//    }
//
//    private fun setCurrentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fragmentContainer, fragment)
//            commit()
//        }
//    }
}}