package ru.payts.retusaari.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ru.payts.retusaari.R


class LogoutDialog(val onLogout: () -> Unit) : DialogFragment() {

    companion object {
        val TAG = LogoutDialog::class.java.name + "TAG"
        fun newInstance(onLogout: () -> Unit) = LogoutDialog(onLogout)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context)
            .setTitle(getString(R.string.main_logout_title))
            .setMessage(getString(R.string.main_logout_message))
            .setPositiveButton(R.string.main_logout_ok) { dialog, which ->
                onLogout()
            }
            .setNegativeButton(R.string.main_logout_cancel) { dialog, which ->
                dismiss()
            }
            .create()
    }
}