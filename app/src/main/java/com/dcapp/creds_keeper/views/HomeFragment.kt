package com.dcapp.creds_keeper.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.adapters.CredListAdapter
import com.dcapp.creds_keeper.db.CredDatabase
import com.dcapp.creds_keeper.models.Cred
import com.dcapp.creds_keeper.repository.CredRepository
import com.dcapp.creds_keeper.views.dialog.EditCredDialog
import com.dcapp.creds_keeper.viewmodels.HomeViewModel
import com.dcapp.creds_keeper.viewmodels.HomeViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    lateinit var btnAddNewCred : FloatingActionButton
    lateinit var rvCredList : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddNewCred = view.findViewById(R.id.btnAddNewCred)
        rvCredList = view.findViewById(R.id.rvCredListHome)

        val credDao = CredDatabase.getDatabase(requireActivity()).credDao()
        val credRepository = CredRepository(credDao)
        val homeViewModel = ViewModelProvider(requireActivity(),HomeViewModelFactory(credRepository))[HomeViewModel::class.java]

        btnAddNewCred.setOnClickListener{
            val addCredDialog = EditCredDialog(requireActivity(), homeViewModel = homeViewModel)
            addCredDialog.apply {
                show()
                setTitle("Add Credential")
            }
        }

        val credListAdapter = CredListAdapter(
            requireActivity(),
            homeViewModel = homeViewModel,
        )
//        credListAdapter.submitList(homeViewModel.getCredLiveList().value)

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
//            lifecycleScope.launch{
//                Log.d("MyTag","Updated")
//                credListAdapter.submitList(it)
//            }

//                creds->credListAdapter.setCreds(creds?: emptyList())
            creds->credListAdapter.submitList(creds)
//            var newCreds = mutableListOf<Cred>()
//            newCreds.addAll(it)
            Log.d("MyTag", "Updated")
        }

    }

}