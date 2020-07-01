package com.example.cryptoassets.infrastructure.util

import android.content.Context
import android.net.ConnectivityManager

class ConnectionUtils {

    companion object {

        fun hasConnection(ctx: Context): Boolean {
            val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            return info != null && info.isConnected
        }
    }
}