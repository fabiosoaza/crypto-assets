package com.example.cryptoassets.core.interactor.listener

interface  OnSalvarAtivo{
    fun onErrorAtivoJaCadastrado(msg: String)
    fun onErrorTickerInvalido(msg: String)
    fun onErrorNomeInvalido(msg: String)
    fun onSuccess(msg: String)
}