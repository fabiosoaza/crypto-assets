package com.example.cryptoassets.ui.interactor

import com.example.cryptoassets.core.domain.Transacao
import com.example.cryptoassets.core.util.MoneyUtils
import org.javamoney.moneta.Money

class ListTransacoesInteractor(private val transacoes: List<Transacao>) {

    val valorTotal: Money get() {
        return calcularValorTotalPago()
    }

    val transacoesInteractor : List<TransacaoInteractor> get(){
        return transacoes.map { transacao ->
            TransacaoInteractor(transacao)
        }
    }

    private fun calcularValorTotalPago(): Money {
        if (transacoesInteractor.isEmpty()) {
            return MoneyUtils.zero()
        }
        return transacoesInteractor.groupingBy { 0 }
            .aggregate { _, accumulator: Money?, element, first ->
                if (first)
                    element.valorTotalPago
                else
                    accumulator?.add(element.valorTotalPago)
            }[0] ?: MoneyUtils.zero()
    }

}