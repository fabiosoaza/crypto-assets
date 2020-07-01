package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.Ticker

interface AtivoCarteiraRepository {

    fun ativos(): Set<AtivoCarteira>
    fun salvar(ativoCarteira: AtivoCarteira)
    fun buscar(ticker: Ticker):AtivoCarteira?

}