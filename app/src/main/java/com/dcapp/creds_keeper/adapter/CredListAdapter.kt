package com.dcapp.creds_keeper.adapter

import android.app.Activity
import android.app.AlertDialog
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.config.Constants
import com.dcapp.creds_keeper.model.Cred

class CredListAdapter(private val context : Activity, private val credList : ArrayList<Cred>, ) : RecyclerView.Adapter<CredListAdapter.CredViewHolder>(){

    inner class CredViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var leadingContainer : ConstraintLayout
        var tvLeading : TextView
        var tvTitle : TextView
        var tvUId : TextView
        var tvPwd : TextView
        var iconMenu : ImageView

        init {
            leadingContainer = view.findViewById(R.id.leadingContainerCredHome)
            tvLeading = view.findViewById(R.id.tvIconCredHome)
            tvTitle = view.findViewById(R.id.tvTitleCredHome)
            tvUId = view.findViewById(R.id.tvUIdCredHome)
            tvPwd = view.findViewById(R.id.tvPwdCredHome)
            iconMenu = view.findViewById(R.id.btnMoreOptionsCredHome)


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
        holder.tvLeading.text = cred.title.substring(0,1)
        holder.tvTitle.text = cred.title
        holder.tvUId.text = cred.uid
        holder.tvPwd.text = cred.pwd
        (holder.leadingContainer.background as GradientDrawable).setColor(
            ContextCompat.getColor(context, Constants.COLOR_LIST[position%Constants.COLOR_LIST.size])
        )

        holder.iconMenu.setOnClickListener{
            val popupMenu = PopupMenu(context,holder.iconMenu)
            popupMenu.apply {
                inflate(R.menu.cred_options)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    setForceShowIcon(true)
                }
                show()
                setOnMenuItemClickListener { menuItem->
                    when(menuItem.itemId){
                        R.id.editCred->{

                        }
                        R.id.deleteCred->{
                            confirmDeleteCred()
                        }
                    }
                    true
                }
            }
        }
        // Set bottom margin at last item
        (holder.itemView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
            0,0,0,
            if(position==itemCount-1) 200 else 0
        )
    }

    private fun confirmDeleteCred(){
        AlertDialog.Builder(context)
            .apply {
                setTitle("Delete Credential")
                setMessage("Are you sure that you want to delete cred?")
                setPositiveButton("Delete"){
                    dialogInterface,i->
                    run {
                        Toast.makeText(context, "Credential Deleted", Toast.LENGTH_SHORT).show()
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

}