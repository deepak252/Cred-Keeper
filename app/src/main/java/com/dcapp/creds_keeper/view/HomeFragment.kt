package com.dcapp.creds_keeper.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.adapter.CredListAdapter
import com.dcapp.creds_keeper.creds

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MyTag", "HomeFragment -> onCreate")
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyTag", "HomeFragment -> onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvCredList: RecyclerView = view.findViewById(R.id.rvCredListHome)
        creds.reverse()
        rvCredList.apply {
            adapter = CredListAdapter(activity as Activity, creds)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    override fun onStart() {
        Log.d("MyTag", "HomeFragment -> onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MyTag", "HomeFragment -> onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MyTag", "HomeFragment -> onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MyTag", "HomeFragment -> onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("MyTag", "HomeFragment -> onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("MyTag", "HomeFragment -> onDestroy")
        super.onDestroy()
    }

}