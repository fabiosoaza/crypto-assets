package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.core.model.entidade.Transacao

interface TransacaoRepository {

    fun transacoes():Set<Transacao>

    fun salvar(transacao: Transacao)

    fun excluir(transacao: Transacao)

    fun excluirTodas(ativo:Ativo)

}