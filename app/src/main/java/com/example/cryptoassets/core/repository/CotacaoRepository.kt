package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.model.entidade.Cotacao

interface CotacaoRepository {

    fun cotacoes():Set<Cotacao>

}