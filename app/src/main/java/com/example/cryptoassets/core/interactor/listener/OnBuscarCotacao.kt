package com.example.cryptoassets.core.interactor.listener

import com.example.cryptoassets.core.model.entidade.Cotacao

interface  OnBuscarCotacao{
    fun onErrorConnection(msg:String)
    fun onErrorBuscarCotacao(msg:String)
    fun onPreExecute()
    fun onPostExecute(result: List<Cotacao>)
}