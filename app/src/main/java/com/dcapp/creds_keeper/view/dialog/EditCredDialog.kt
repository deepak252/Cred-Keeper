package com.dcapp.creds_keeper.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.viewmodel.BookmarksViewModel
import com.dcapp.creds_keeper.viewmodel.HomeViewModel

class EditCredDialog(
    context: Context,
    private var cred: Cred?=null,
    private val homeViewModel: HomeViewModel?=null,
    private val bookmarksViewModel: BookmarksViewModel?=null
) : Dialog(context) {
    init {
        setContentView(R.layout.dialog_edit_cred)
        val edtTitle = findViewById<EditText>(R.id.edtTitleCred)
        val edtUid = findViewById<EditText>(R.id.edtUidCred)
        val edtPwd = findViewById<EditText>(R.id.edtPwdCred)
        val btnSave = findViewById<TextView>(R.id.btnSaveCred)
        val btnCancel = findViewById<TextView>(R.id.btnExitCredDialog)

        if(cred!=null){
            edtTitle.setText(cred!!.title)
            edtUid.setText(cred!!.uid)
            edtPwd.setText(cred!!.pwd)
        }

        btnSave.setOnClickListener{
            val cred2 = Cred(
                cred?.id?:15,
                edtTitle.text.toString(),
                edtUid.text.toString(),
                edtPwd.text.toString(),
                "",
                cred?.isBookmarked?:false
            )
            Log.d("MyTag", cred2.toString())
            if(cred!=null){
                homeViewModel?.addOrUpdateCred(cred2)
                bookmarksViewModel?.updateCred(cred2)
            }else{
                homeViewModel?.addOrUpdateCred(cred2)
            }
            dismiss()
        }

        btnCancel.setOnClickListener{
            dismiss()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}