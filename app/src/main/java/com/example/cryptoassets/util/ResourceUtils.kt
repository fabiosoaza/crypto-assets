package com.example.cryptoassets.util

import android.content.Context
import android.content.res.Resources
import com.example.cryptoassets.R
import com.example.cryptoassets.core.model.entidade.Ticker

class ResourceUtils {
    companion object {

        fun getStringResourceByName(context: Context, aString: String, packageName:String?= context.packageName): String? {
            return try {
                val resId: Int = context.resources.getIdentifier(aString, "string", packageName)
                context.getString(resId)
            } catch(ex: Resources.NotFoundException){
                null
            }
        }

        fun getAssetImageResourceIdByTicker(ticker: Ticker):Int{
            return when(ticker){
                Ticker.BTC-> R.drawable.ic_btc
                Ticker.BCH-> R.drawable.ic_bch
                Ticker.LTC-> R.drawable.ic_ltc
                Ticker.XRP-> R.drawable.ic_xrp
                Ticker.ETH-> R.drawable.ic_eth
                Ticker.USDC-> R.drawable.ic_usdc
                else -> R.drawable.ic_currency_not_found
            }
        }

        fun getString(context: Context, resourceId: Int): String? {
            return context.getString(resourceId)
        }
    }
}