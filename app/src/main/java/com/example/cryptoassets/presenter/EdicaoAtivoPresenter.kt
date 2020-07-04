package com.example.cryptoassets.presenter

import com.example.cryptoassets.core.interactor.AtivoInteractor
import com.example.cryptoassets.ui.view.AtivoView

class EdicaoAtivoPresenter(private val view:AtivoView, private val interactor:AtivoInteractor) {

    fun salvar(ticker:String?, nome:String?){
        interactor.salvar(ticker, nome, view)
    }

}