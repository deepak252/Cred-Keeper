package com.dcapp.creds_keeper.adapter

import android.app.ActionBar.LayoutParams
import android.app.Activity
import android.app.AlertDialog
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.config.Constants
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.view.dialog.EditCredDialog
import com.dcapp.creds_keeper.viewmodel.BookmarksViewModel
import com.dcapp.creds_keeper.viewmodel.HomeViewModel

//class CredListAdapter(private val context : Activity, private val credLiveList : LiveData<ArrayList<Cred>> ) : RecyclerView.Adapter<CredListAdapter.CredViewHolder>(){
class CredListAdapter(
    private val context : Activity,
    private val homeViewModel: HomeViewModel?=null,
    private val bookmarksViewModel: BookmarksViewModel?=null,

) : RecyclerView.Adapter<CredListAdapter.CredViewHolder>(){
    private var credList = listOf<Cred>()

    init {
        if(homeViewModel!=null){
            credList = homeViewModel.getCredLiveList().value?: emptyList()
        }else if(bookmarksViewModel!=null){
            credList = bookmarksViewModel.getBookmarkCredLiveList().value?: emptyList()
        }
//        credLiveList.observe(context as LifecycleOwner){
//            notifyDataSetChanged()
//        }
    }

    fun setCreds(creds : List<Cred>){
        credList = creds
        notifyDataSetChanged()
    }

    inner class CredViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var leadingContainer : ConstraintLayout
        var tvLeading : TextView
        var tvTitle : TextView
        var tvUId : TextView
        var tvPwd : TextView
        var btnMoreOptions : ImageView
        var btnBookmark : ImageView

        init {
            leadingContainer = view.findViewById(R.id.leadingContainerCredHome)
            tvLeading = view.findViewById(R.id.tvIconCredHome)
            tvTitle = view.findViewById(R.id.tvTitleCredHome)
            tvUId = view.findViewById(R.id.tvUIdCredHome)
            tvPwd = view.findViewById(R.id.tvPwdCredHome)
            btnMoreOptions = view.findViewById(R.id.btnMoreOptionsCredHome)
            btnBookmark = view.findViewById(R.id.btnBookmarkCredHome)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CredViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cred_main, parent, false)
        return CredViewHolder(view)
    }

    override fun getItemCount(): Int {
        return credList.size
    }

    override fun onBindViewHolder(holder: CredViewHolder, position: Int) {
        val cred = credList[position]
        if(cred.title.isNotEmpty()){
            holder.tvLeading.text = cred.title.substring(0,1)
        }else{
            holder.tvLeading.text = "#"
        }
        holder.tvTitle.text = cred.title
        holder.tvUId.text = cred.uid
        holder.tvPwd.text = cred.pwd
        (holder.leadingContainer.background as GradientDrawable).setColor(
            ContextCompat.getColor(context, Constants.COLOR_LIST[position%Constants.COLOR_LIST.size])
        )
        // Set bottom margin at last item
//        (holder.itemView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
//            0,0,0,
//            if(position==itemCount-1) 200 else 0
//        )

        if(cred.isBookmarked){
            holder.btnBookmark.setImageResource(R.drawable.outline_bookmark_24)
            holder.btnBookmark.setColorFilter(
                ContextCompat.getColor(context, R.color.orange)
            )
        }else{
            holder.btnBookmark.setImageResource(R.drawable.round_bookmark_border_24)
            holder.btnBookmark.setColorFilter(
                ContextCompat.getColor(context, R.color.black_500)
            )
        }

        holder.btnBookmark.setOnClickListener{
            toggleBookmarkCred(cred)
        }

        holder.btnMoreOptions.setOnClickListener{
            val popupMenu = PopupMenu(context,holder.btnMoreOptions)
            popupMenu.apply {
                inflate(R.menu.cred_options)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    setForceShowIcon(true)
                }
                show()
                setOnMenuItemClickListener { menuItem->
                    when(menuItem.itemId){
                        R.id.editCred->{
                            editCred(cred)
                        }
                        R.id.deleteCred->{
                            confirmDeleteCred(cred)
                        }
                    }
                    true
                }
            }
        }
    }

    private fun editCred(cred : Cred){
        val editCredDialog = EditCredDialog(context, cred, homeViewModel, bookmarksViewModel)
        editCredDialog.apply {
            show()
            setTitle("Edit Credential")
        }
    }

    private fun confirmDeleteCred(cred : Cred){
        AlertDialog.Builder(context)
            .apply {
                setTitle("Delete Credential")
                setMessage("Are you sure that you want to delete cred?")
                setPositiveButton("Delete"){
                    dialogInterface,i->
                    run {
                        Toast.makeText(context, "Credential Deleted", Toast.LENGTH_SHORT).show()
                        deleteCred(cred)
                        dialogInterface.cancel()
                    }
                }

                setNegativeButton( "Cancel") { dialogInterface, i ->
                    run {
                        dialogInterface.cancel()
                    }
                }
                show()
            }
    }

    private fun toggleBookmarkCred(cred : Cred){
        homeViewModel?.toggleBookmark(cred)
        bookmarksViewModel?.toggleBookmark(cred)
    }

    fun deleteCred(cred : Cred){
        if(homeViewModel!=null){
            homeViewModel.deleteCred(cred)
        }else{
            bookmarksViewModel?.deleteCred(cred)
        }
    }


}