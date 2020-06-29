package com.example.cryptoassets.ui.util

import android.text.Editable
import android.text.TextWatcher
import android.view.accessibility.AccessibilityEvent
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputLayout

class UiUtils {

    companion object {

        fun showFragment(fragmentManager : FragmentManager?, containerId: Int?, fragment: Fragment?){
            val transaction = fragmentManager?.beginTransaction()
            if (containerId != null && fragment != null) {
                    transaction?.replace(containerId, fragment)
            }
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        fun closeAndGoBack(fragmentManager : FragmentManager?, fragment: Fragment){
            fragmentManager?.beginTransaction()?.remove(fragment)?.commit()
            fragmentManager?.popBackStack()
        }

        fun message(view: ComponentActivity, message: String) {
            view.runOnUiThread {
                Toast.makeText(
                    view,
                    message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

         fun createClearInputErrorMessageListener(text: TextInputLayout): TextWatcher {
            return object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int ) { }
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int ) {
                    text.error = ""
                }
            }
        }

        fun showInputTextError(editText: EditText?, textViewMessage: TextInputLayout?, mensagem: String ) {
            textViewMessage?.error = mensagem
            editText?.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
            editText?.requestFocus();
        }

    }

}