package com.example.cryptoassets.ui.interactor

import com.example.cryptoassets.core.domain.Cotacao

interface  OnBuscaCotacao{
    fun onErrorConnection(msg:String)
    fun onErrorBuscaCotacao(msg:String)
    fun onPreExecute()
    fun onPostExecute(result: List<Cotacao>)
}