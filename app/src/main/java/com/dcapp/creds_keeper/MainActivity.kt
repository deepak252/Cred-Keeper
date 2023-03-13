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

var creds = arrayListOf<Cred>(
    Cred("Codechef", "deepak@gmail.com", "1234",""),
    Cred("Codeforces", "deepak@gmail.com", "981234",""),
    Cred("Gmail", "deepak@gmail.com", "SD1234",""),
    Cred("Google", "deepak@gmail.com", "##1234",""),
    Cred("Facebook", "deepak@gmail.com", "GHD1234",""),
    Cred("Twitter", "deepak@gmail.com", "0931234",""),
)

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigation : BottomNavigationView
    private val homeFragment = HomeFragment()
    private val bookmarksFragment = BookmarksFragment()
    private val profileFragment = ProfileFragment()
//    lateinit var rvCredList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        creds.addAll(creds)
        creds.addAll(creds)
        creds.addAll(creds)

        initBottomNav()



//        rvCredList = findViewById(R.id.rvCredsHome)
//
//        rvCredList.adapter = HomeCredListAdapter(this,creds)
//        rvCredList.layoutManager = LinearLayoutManager(this)
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