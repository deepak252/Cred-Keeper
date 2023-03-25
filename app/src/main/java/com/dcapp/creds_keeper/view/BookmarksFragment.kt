package com.dcapp.creds_keeper.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.adapter.CredListAdapter
import com.dcapp.creds_keeper.repository.CredRepository
import com.dcapp.creds_keeper.viewmodel.BookmarksViewModel
import com.dcapp.creds_keeper.viewmodel.BookmarksViewModelFactory

class BookmarksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvCredList: RecyclerView = view.findViewById(R.id.rvCredListBookmarks)

        val bookmarksViewModel = ViewModelProvider(requireActivity(),BookmarksViewModelFactory(
            CredRepository.getInstance()
        ))[BookmarksViewModel::class.java]

        val credListAdapter = CredListAdapter(
            activity as Activity,
            bookmarksViewModel = bookmarksViewModel
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

        bookmarksViewModel.getBookmarkCredLiveList().observe(requireActivity()){
            creds->credListAdapter.setCreds(creds)
        }
    }

}