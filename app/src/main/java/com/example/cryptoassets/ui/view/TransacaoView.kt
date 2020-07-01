package com.example.cryptoassets.ui.view

import com.example.cryptoassets.ui.interactor.OnBuscaCotacao
import com.example.cryptoassets.ui.interactor.TransacaoInteractor

interface TransacaoView : TransacaoInteractor.OnCliqueSalvar, OnBuscaCotacao {
}