package com.example.cryptoassets.core.model.entidade

import com.example.cryptoassets.util.MoneyUtils
import org.javamoney.moneta.Money

class Carteira(private val ativoCarteiras: MutableList<AtivoCarteira>? = mutableListOf<AtivoCarteira>()) {

    val investimentos : MutableList<AtivoCarteira> get() = this.ativoCarteiras ?: mutableListOf<AtivoCarteira>()

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