package com.example.cryptoassets.ui.interactor

import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.CalculadorVariacaoCotacao
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.core.domain.Transacao
import org.javamoney.moneta.Money
import java.math.BigDecimal
import java.time.LocalDateTime

class TransacaoInteractor(private val transacao:Transacao) {

    val tipoTransacao : String get() = transacao.tipo.toString()
    val nomeAtivo : String get() = transacao.ativo.ativo.nome
    val data : LocalDateTime = transacao.data
    val quantidade : BigDecimal get() = transacao.ativo.quantidade
    val precoMedio: Money get() = transacao.ativo.precoMedio
    val valorTotalPago: Money get() = transacao.ativo.calcularTotalPago()


}