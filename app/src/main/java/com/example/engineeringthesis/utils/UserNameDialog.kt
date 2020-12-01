package com.example.engineeringthesis.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.engineeringthesis.R
import kotlinx.android.synthetic.main.username_dialog.view.*


class UserNameDialog()  : AppCompatDialogFragment() {
    private lateinit var listener: UserNameDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val li = LayoutInflater.from(activity)
        val promptsView: View = li.inflate(R.layout.username_dialog, null)
        val alertDialogBuilder = AlertDialog.Builder(
                activity)

        alertDialogBuilder.setView(promptsView)
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, id -> // get user input and set it to result

                   val mess =  promptsView.edittext_dialog_username.text.toString()
                    listener.yesClicked(mess)

                }
                .setNegativeButton("Cancel"
                ) { dialog, id -> //dialog.cancel()
                    val mess = "Empty"
                    listener.noClicked(mess)
                    dialog.dismiss()
                }
        return alertDialogBuilder.create()
    }

     interface UserNameDialogListener {
        fun yesClicked(message:String)

        fun noClicked(message:String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as UserNameDialogListener
        }
        catch (e: ClassCastException) {
            e.toString()
        }
    }
}