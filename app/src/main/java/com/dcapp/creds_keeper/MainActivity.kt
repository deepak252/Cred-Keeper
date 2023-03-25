package com.dcapp.creds_keeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.view.BookmarksFragment
import com.dcapp.creds_keeper.view.HomeFragment
import com.dcapp.creds_keeper.view.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
//
//var creds = arrayListOf<Cred>(
//    Cred(0,"Coding Ninja", "deepak@gmail.com", "1234","",false),
//    Cred(1,"Codeforces", "deepak@gmail.com", "981234","",false),
//    Cred(2,"Gmail", "deepak@gmail.com", "SD1234","",false),
//    Cred(3,"Google", "deepak@gmail.com", "##1234","",false),
//    Cred(4,"Facebook", "deepak@gmail.com", "GHD1234","",false),
//    Cred(5,"Twitter", "deepak@gmail.com", "0931234","",false),
//    Cred(6,"Instagram", "deepak@gmail.com", "ERER013234","",false),
//    Cred(7,"Flipkart", "deepak@gmail.com", "FLIP@#$@","",false),
//)

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigation : BottomNavigationView
    private val homeFragment = HomeFragment()
    private val bookmarksFragment = BookmarksFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNav()

    }

    private fun initBottomNav(){
        bottomNavigation = findViewById(R.id.bottomNav)

        loadFragment(homeFragment)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameDashboard,homeFragment)
            .commit()
        bottomNavigation.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.navHome->{
                    if(!homeFragment.isAdded){
                        loadFragment(homeFragment)
                    }else{
                        Toast.makeText(this,"Already in home", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.navBookmarks->{
                    if(!bookmarksFragment.isAdded){
                        loadFragment(bookmarksFragment)
                    }else{
                        Toast.makeText(this,"Already in bookmarks", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.navProfile->{
                    if(!profileFragment.isAdded){
                        loadFragment(profileFragment)
                    }else{
                        Toast.makeText(this,"Already in profile", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            true
        }

    }

    private fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameDashboard,fragment)
            .commit()
    }

}