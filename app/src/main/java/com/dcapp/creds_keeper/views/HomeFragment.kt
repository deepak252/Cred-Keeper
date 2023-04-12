package com.dcapp.creds_keeper.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.adapters.CredListAdapter
import com.dcapp.creds_keeper.utils.Logger
import com.dcapp.creds_keeper.views.dialog.EditCredDialog
import com.dcapp.creds_keeper.viewmodels.HomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var btnAddNewCred : FloatingActionButton
    lateinit var rvCredList : RecyclerView
    lateinit var homeViewModel: HomeViewModel

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

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        btnAddNewCred.setOnClickListener{
            val addCredDialog = EditCredDialog(requireActivity(), homeViewModel = homeViewModel)
            addCredDialog.apply {
                show()
                setTitle("Add Credential")
            }
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
            creds->credListAdapter.setCreds(creds?: emptyList())
            Logger.message("Home Observer")
        }

    }

}