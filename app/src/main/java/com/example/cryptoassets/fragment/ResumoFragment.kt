package com.example.cryptoassets.fragment

import com.example.cryptoassets.R
import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.core.util.MoneyUtils
import com.example.cryptoassets.ui.adapter.ListAtivosCarteiraAdapter
import com.example.cryptoassets.ui.view.ListAtivosCarteiraView
import com.example.cryptoassets.ui.view.impl.builder.ListAtivosCarteiraViewBuilder
import org.javamoney.moneta.Money
import kotlin.random.Random


class ResumoFragment : ListItemsFragmentBase() {

    private var listAdapter =
        ListAtivosCarteiraAdapter(
            ListAtivosCarteiraViewBuilder()
                .build()
        )

    override fun recyclerViewId() = R.id.rvListagemMoedas

    override fun fragmentLayoutId() = R.layout.fragment_resumo

    override fun listAdapter() = listAdapter

    override fun dataSet(): ListAtivosCarteiraView {
        return ListAtivosCarteiraViewBuilder()
            .cotacoes(cotacoes())
            .ativos(ativos())
            .build()
    }

    private fun cotacoes(): MutableList<Cotacao> {
        return beanFactory().ativoRepository().ativos().map { ativo ->
            Cotacao(
                ativo,
                randomMoney()
            )
        }.toMutableList()
    }

    private fun ativos(): MutableList<AtivoCarteira> {
        return beanFactory().ativoRepository().ativos().map { ativo ->
            AtivoCarteira(
                ativo,
                Random.nextDouble(0.001, 3.0).toBigDecimal(),
                randomMoney()
            )
        }.toMutableList()
    }

    private fun randomMoney() =
        Money.of(Random.nextDouble(140.0, 45_000.0), MoneyUtils.getCurrency())

}
