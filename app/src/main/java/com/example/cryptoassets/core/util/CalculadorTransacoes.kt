package com.example.cryptoassets.core.util

import com.example.cryptoassets.core.domain.TipoTransacao
import com.example.cryptoassets.core.domain.Transacao
import org.javamoney.moneta.Money

class CalculadorTransacoes {

    fun calcularValorTotal(transacoes: List<Transacao>): Money {
        if (transacoes.isEmpty()) {
            return MoneyUtils.zero()
        }
        return transacoes.groupingBy { 0 }
            .aggregate { _, accumulator: Money?, element, first ->
                if (first)
                    valor(element)
                else
                    accumulator?.add(valor(element))
            }[0] ?: MoneyUtils.zero()
    }

    private fun valor(transacao: Transacao): Money {
        val valor = transacao.ativo.calcularTotalPago()
        return if (transacao.tipo == TipoTransacao.VENDA) {
            valor.negate()
        } else {
            valor.plus()
        }
    }


}

