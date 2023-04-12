package com.dcapp.creds_keeper

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dcapp.creds_keeper.databinding.ActivityMainBinding
import com.dcapp.creds_keeper.utils.KeyboardUtils
import com.dcapp.creds_keeper.views.BookmarksFragment
import com.dcapp.creds_keeper.views.HomeFragment
import com.dcapp.creds_keeper.views.ProfileFragment
import com.dcapp.creds_keeper.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val bookmarksFragment = BookmarksFragment()
    private val profileFragment = ProfileFragment()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var bookmarksViewModel: BookmarksViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        bookmarksViewModel = ViewModelProvider(this)[BookmarksViewModel::class.java]
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.mainViewModel = mainViewModel

        setToolbar()
        setBottomNav()

    }

    private fun setToolbar(){
        binding.appbarMain.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.appbarMain.animate().apply {
            duration = 2000
        }

        binding.btnSearch.setOnClickListener {
            mainViewModel.toggleSearch()
            binding.edtSearchCred.requestFocus()
            KeyboardUtils.showKeyboard(this, binding.edtSearchCred)
        }

        binding.btnCancelSearch.setOnClickListener {
            if(binding.edtSearchCred.text.isNotEmpty()){
                binding.edtSearchCred.text.clear()
            }else{
                mainViewModel.toggleSearch()
                binding.edtSearchCred.clearFocus()
                KeyboardUtils.hideKeyboard(this, binding.edtSearchCred)
                if(mainViewModel.bottomNavIndexLiveData.value==0){
                    homeViewModel.clearSearch()
                }else if(mainViewModel.bottomNavIndexLiveData.value==1){
                    bookmarksViewModel.clearSearch()
                }
            }
        }

        binding.edtSearchCred.addTextChangedListener {
            if(mainViewModel.bottomNavIndexLiveData.value==0){
                homeViewModel.searchCred(it.toString())
            }else if(mainViewModel.bottomNavIndexLiveData.value==1){
                bookmarksViewModel.searchCred(it.toString())
            }
        }
    }

    private fun setBottomNav(){
        loadFragment(homeFragment)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameDashboard,homeFragment)
            .commit()
        binding.bottomNav.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.navHome->{
                    if(!homeFragment.isAdded){
                        mainViewModel.updateBottomNavIndex(0)
                        loadFragment(homeFragment)
                    }else{
                        Toast.makeText(this,"Already in home", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.navBookmarks->{
                    if(!bookmarksFragment.isAdded){
                        mainViewModel.updateBottomNavIndex(1)
                        loadFragment(bookmarksFragment)
                    }else{
                        Toast.makeText(this,"Already in bookmarks", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.navProfile->{
                    if(!profileFragment.isAdded){
                        mainViewModel.updateBottomNavIndex(2)
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