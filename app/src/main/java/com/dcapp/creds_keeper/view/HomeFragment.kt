package com.dcapp.creds_keeper.view

import android.app.ActionBar.LayoutParams
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.adapter.CredListAdapter
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.repository.CredRepository
import com.dcapp.creds_keeper.view.dialog.EditCredDialog
import com.dcapp.creds_keeper.viewmodel.HomeViewModel
import com.dcapp.creds_keeper.viewmodel.HomeViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    lateinit var btnAddNewCred : FloatingActionButton
    lateinit var rvCredList : RecyclerView
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
        btnAddNewCred = view.findViewById(R.id.btnAddNewCred)
        rvCredList = view.findViewById(R.id.rvCredListHome)

        val homeViewModel = ViewModelProvider(requireActivity(),HomeViewModelFactory(CredRepository.getInstance()))[HomeViewModel::class.java]

        btnAddNewCred.setOnClickListener{
            val addCredDialog = EditCredDialog(requireActivity(), homeViewModel = homeViewModel)
            addCredDialog.show()
            addCredDialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        }
        val credListAdapter = CredListAdapter(
            requireActivity(),
            homeViewModel = homeViewModel
        )

        rvCredList.apply {
            adapter = credListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        homeViewModel.getCredLiveList().observe(requireActivity()) {
            creds->credListAdapter.setCreds(creds)
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