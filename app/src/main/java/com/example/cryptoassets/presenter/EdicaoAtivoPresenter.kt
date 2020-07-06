package com.example.cryptoassets.presenter

import com.example.cryptoassets.core.interactor.AtivoInteractor
import com.example.cryptoassets.core.interactor.listener.OnExcluirAtivo
import com.example.cryptoassets.core.interactor.listener.OnSalvarAtivo

class EdicaoAtivoPresenter(private val interactor:AtivoInteractor) {

    fun salvar(ticker:String?, nome:String?, listener: OnSalvarAtivo){
        interactor.salvar(ticker, nome, listener)
    }

    fun excluir(ticker:String?, listener:OnExcluirAtivo){
        interactor.excluir(ticker, listener)
    }

}