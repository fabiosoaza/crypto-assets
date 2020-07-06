package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Ticker

interface AtivoCarteiraRepository {

    fun ativos(): Set<AtivoCarteira>
    fun salvar(ativoCarteira: AtivoCarteira)
    fun buscar(ticker: Ticker): AtivoCarteira?
    fun excluir(ativo: Ativo)
}