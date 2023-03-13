package com.dcapp.creds_keeper.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dcapp.creds_keeper.R

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MyTag","ProfileFragment -> onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyTag","ProfileFragment -> onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        Log.d("MyTag","ProfileFragment -> onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MyTag","ProfileFragment -> onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MyTag","ProfileFragment -> onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MyTag","ProfileFragment -> onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("MyTag","ProfileFragment -> onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("MyTag","ProfileFragment -> onDestroy")
        super.onDestroy()
    }

}