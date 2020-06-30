package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.domain.Cotacao

interface CotacaoRepository {

    fun cotacoes():Set<Cotacao>

}