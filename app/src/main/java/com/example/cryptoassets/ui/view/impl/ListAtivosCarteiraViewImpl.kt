package com.example.cryptoassets.ui.view.impl

import com.example.cryptoassets.ui.interactor.ListAtivosCarteiraInteractor
import com.example.cryptoassets.ui.util.FormatadorUtils
import com.example.cryptoassets.ui.view.AtivoCarteiraView
import com.example.cryptoassets.ui.view.ListAtivosCarteiraView
import org.javamoney.moneta.Money
import java.math.BigDecimal

class ListAtivosCarteiraViewImpl(private val carteiraInteractor: ListAtivosCarteiraInteractor) : ListAtivosCarteiraView {

    override fun getSaldoFormatado(): String {
        val valor = carteiraInteractor.valorSaldo
        return formatar(valor)
    }

    override fun getVariacaoTotalFormatada(): String {
        return formatar(carteiraInteractor.variacaoValor)
    }

    override fun getVariacaoPorcentagemFormatada(): String {
        return formatar(carteiraInteractor.variacaoPorcentagem)
    }

    private fun formatar(valor: Money) = FormatadorUtils.formatarValor(valor)
    private fun formatar(valor: BigDecimal) = FormatadorUtils.formatarValor(valor)


    override fun getAtivos(): List<AtivoCarteiraView> {
        return carteiraInteractor.ativoCarteiras.map { ativo -> AtivoCarteiraViewImpl(ativo) }
    }
}