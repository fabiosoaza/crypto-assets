package com.example.cryptoassets.core.domain

import com.example.cryptoassets.core.util.MoneyUtils
import org.javamoney.moneta.Money

class Carteira(private val ativoCarteiras: MutableList<AtivoCarteira>? = mutableListOf<AtivoCarteira>()) {

    val investimentos : MutableList<AtivoCarteira> get() = this.ativoCarteiras ?: mutableListOf<AtivoCarteira>()

    fun adicionar(ativoCarteira: AtivoCarteira) {
        ativoCarteiras?.add(ativoCarteira)
    }

    fun calcularValorTotalPago(): Money {
        if (ativoCarteiras == null || ativoCarteiras.isEmpty()) {
            return MoneyUtils.zero()
        }
        return ativoCarteiras.groupingBy { 0 }
            .aggregate { _, accumulator: Money?, element, first ->
                if (first)
                    element.calcularTotalPago()
                else
                    accumulator?.add(element.calcularTotalPago())
            }[0] ?: MoneyUtils.zero()
    }

}