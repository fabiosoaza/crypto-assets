package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.domain.Ativo
import com.example.cryptoassets.core.domain.Ticker

interface AtivoRepository {

    fun ativos():Set<Ativo>

    fun salvar(ativo: Ativo)

    fun excluir(ativo:Ativo)

    fun findByTicker(ticker:Ticker):Ativo?
}