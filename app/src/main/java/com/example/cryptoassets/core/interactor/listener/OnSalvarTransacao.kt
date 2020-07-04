package com.example.cryptoassets.core.interactor.listener

interface  OnSalvarTransacao{
    fun onErrorQuantidadeInvalida(msg: String)
    fun onErrorPrecoMedioInvalido(msg: String)
    fun onErrorTickerInvalido(msg: String)
    fun onErrorTipoTransacaoInvalida(msg: String)
    fun onErrorDataTransacaoInvalida(msg: String)
    fun onSuccess(msg: String)
}