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

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MyTag", "BookmarksFragment -> onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyTag", "BookmarksFragment -> onCreateView")
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

    override fun onStart() {
        Log.d("MyTag", "BookmarksFragment -> onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MyTag", "BookmarksFragment -> onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MyTag", "BookmarksFragment -> onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MyTag", "BookmarksFragment -> onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("MyTag", "BookmarksFragment -> onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("MyTag", "BookmarksFragment -> onDestroy")
        super.onDestroy()
    }

}