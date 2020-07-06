package com.example.cryptoassets.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
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
             val listener: (CharSequence, Int, Int, Int) -> Unit = { _, _, _, _ -> text.error = "" }
             return createOnTexChangeListener(text, listener)
         }



        fun createOnTexChangeListener(text: TextInputLayout, listener: (CharSequence, Int, Int, Int) -> Unit ): TextWatcher {
            return object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int ) { }
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int ) {
                    listener(s, start, before, count)
                }
            }
        }

        fun showInputTextError(editText: EditText?, textViewMessage: TextInputLayout?, mensagem: String ) {
            textViewMessage?.error = mensagem
            editText?.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
            editText?.requestFocus();
        }

        fun sendTalkBackMessage(context: Context, message: String){
            val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
            if (accessibilityManager.isEnabled) {
                val accessibilityEvent = AccessibilityEvent.obtain()
                accessibilityEvent.eventType = AccessibilityEvent.TYPE_ANNOUNCEMENT
                accessibilityEvent.text.add(message)
                accessibilityManager.sendAccessibilityEvent(accessibilityEvent)
            }

        }

        fun animateView(view: View, toVisibility: Int, toAlpha: Float, duration: Int) {
            val show = toVisibility == View.VISIBLE
            if (show) {
                view.bringToFront()
                view.alpha = 0F
            }
            view.visibility = View.VISIBLE
            view.animate()
                .setDuration(duration.toLong())
                .alpha((if (show) toAlpha else 0F))
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        view.visibility = toVisibility
                    }
                })
        }

    }

}