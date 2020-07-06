package com.example.cryptoassets.core.interactor.listener

interface  OnExcluirTransacao{
    fun onError(msg: String)
    fun onSuccess(msg: String)
}