package com.example.cryptoassets.ui.component

import android.view.View
import androidx.activity.ComponentActivity
import com.example.cryptoassets.R
import com.example.cryptoassets.ui.util.UiUtils
import java.util.concurrent.atomic.AtomicBoolean

class ProgressBarComponent(private var view: ComponentActivity, private val viewContainerId:Int,  private val viewProgressBarId:Int ) {

    private var container: View = view.findViewById(viewContainerId)
    private var progressOverlay: View = view.findViewById(viewProgressBarId)
    private var showingProgressBar : AtomicBoolean = AtomicBoolean(false)

    fun show(){
        if(showingProgressBar.compareAndSet(false, true)){
            container.visibility=View.GONE
            UiUtils.sendTalkBackMessage(view.baseContext, view.getString(R.string.labelDownloadingData))
            UiUtils.animateView(progressOverlay, View.VISIBLE, 0.4F, 200)
        }
    }

    fun hide(){
        if(showingProgressBar.compareAndSet(true, false)) {
            container.visibility=View.VISIBLE
            UiUtils.sendTalkBackMessage(view.baseContext, view.getString(R.string.labelDownloadingDataComplete))
            UiUtils.animateView(progressOverlay, View.GONE, 0F, 200)
        }
    }

}