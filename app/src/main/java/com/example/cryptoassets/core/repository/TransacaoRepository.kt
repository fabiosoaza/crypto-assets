package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.domain.Ativo
import com.example.cryptoassets.core.domain.Ticker
import com.example.cryptoassets.core.domain.Transacao

interface TransacaoRepository {

    fun transacoes():Set<Transacao>

    fun salvar(transacao: Transacao)

    fun excluir(transacao:Transacao)

}