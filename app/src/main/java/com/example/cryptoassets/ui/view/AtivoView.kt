package com.example.cryptoassets.ui.view

import com.example.cryptoassets.ui.interactor.AtivoInteractor

interface AtivoView : AtivoInteractor.OnCliqueSalvar {

    fun ticker() : String
    fun nome() : String



}