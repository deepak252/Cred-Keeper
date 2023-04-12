package com.dcapp.creds_keeper.views

import android.app.Activity
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
import com.dcapp.creds_keeper.viewmodels.BookmarksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksFragment : Fragment() {
    lateinit var rvCredList : RecyclerView
    lateinit var bookmarksViewModel : BookmarksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCredList = view.findViewById(R.id.rvCredListBookmarks)

        bookmarksViewModel = ViewModelProvider(requireActivity())[BookmarksViewModel::class.java]

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
            Logger.message("Bookmarks Observer")
        }
    }

}