package com.example.cryptoassets.ui.view

import com.example.cryptoassets.core.interactor.AtivoInteractor
import com.example.cryptoassets.core.interactor.listener.OnSalvarAtivo

interface AtivoView : OnSalvarAtivo {

    fun ticker() : String
    fun nome() : String



}