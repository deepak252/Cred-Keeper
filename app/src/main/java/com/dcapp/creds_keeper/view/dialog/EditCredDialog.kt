package com.dcapp.creds_keeper.view.dialog

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.dcapp.creds_keeper.R
import com.dcapp.creds_keeper.model.Cred
import com.dcapp.creds_keeper.utils.KeyboardUtils
import com.dcapp.creds_keeper.viewmodel.BookmarksViewModel
import com.dcapp.creds_keeper.viewmodel.HomeViewModel

class EditCredDialog(
    context: Context,
    private var cred: Cred? = null,
    private val homeViewModel: HomeViewModel? = null,
    private val bookmarksViewModel: BookmarksViewModel? = null
) : Dialog(context) {

    private lateinit var tvDialogTitle: TextView
    private lateinit var edtTitle: EditText
    private lateinit var edtUid: EditText
    private lateinit var edtPwd: EditText
    private lateinit var btnSave: TextView
    private lateinit var btnCancel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_edit_cred)

        initViews()

        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        window?.decorView?.setOnTouchListener { _, motionEvent ->
            clearFocusAll()
            true
        }

    }

    private fun initViews() {
        edtTitle = findViewById(R.id.edtTitleCred)
        edtUid = findViewById(R.id.edtUidCred)
        edtPwd = findViewById(R.id.edtPwdCred)
        btnSave = findViewById(R.id.btnSaveCred)
        btnCancel = findViewById(R.id.btnExitCredDialog)
        tvDialogTitle = findViewById(R.id.tvCredDialogTitle)

        if (cred != null) {
            edtTitle.setText(cred!!.title)
            edtUid.setText(cred!!.uid)
            edtPwd.setText(cred!!.pwd)
        }

        btnSave.setOnClickListener {
            val cred2 = Cred(
                cred?.id ?: 15,
                edtTitle.text.toString(),
                edtUid.text.toString(),
                edtPwd.text.toString(),
                "",
                cred?.isBookmarked ?: false
            )
//            Log.d("MyTag", cred2.toString())
            if (cred != null) {
                homeViewModel?.addOrUpdateCred(cred2)
                bookmarksViewModel?.updateCred(cred2)
            } else {
                homeViewModel?.addOrUpdateCred(cred2)
            }
            dismiss()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    fun setTitle(title: String) {
        tvDialogTitle.text = title
    }

    private fun clearFocusAll() {
        edtTitle.clearFocus()
        edtUid.clearFocus()
        edtPwd.clearFocus()
        KeyboardUtils.hideKeyboard(context, edtTitle)
    }

//    private fun hideKeyboard(view: View) {
//        // Hide the soft keyboard when the user taps outside an EditText
//        val inputMethodManager =
//            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//    }
}