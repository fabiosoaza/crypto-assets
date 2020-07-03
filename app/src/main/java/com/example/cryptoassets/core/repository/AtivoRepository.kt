package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.Ticker

interface AtivoRepository {

    fun ativos():Set<Ativo>

    fun salvar(ativo: Ativo)

    fun excluir(ativo: Ativo)

    fun findByTicker(ticker: Ticker): Ativo?
}