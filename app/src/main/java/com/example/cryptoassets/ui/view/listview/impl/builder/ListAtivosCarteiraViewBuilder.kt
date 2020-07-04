package com.example.cryptoassets.ui.view.listview.impl.builder

import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Carteira
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.ui.view.listview.ListAtivosCarteiraView
import com.example.cryptoassets.ui.view.listview.impl.ListAtivosCarteiraViewImpl

class ListAtivosCarteiraViewBuilder {

    private var cotacoes : MutableList<Cotacao> = mutableListOf()
    private var ativoCarteiras : MutableList<AtivoCarteira> = mutableListOf()

    fun cotacoes(cotacoes: MutableList<Cotacao>) = apply { this.cotacoes = cotacoes }

    fun ativos(ativoCarteiras: MutableList<AtivoCarteira>) = apply {
        this.ativoCarteiras = ativoCarteiras
    }

    fun build(): ListAtivosCarteiraView {
        val carteira =
            Carteira(ativoCarteiras)
        return ListAtivosCarteiraViewImpl(carteira, cotacoes)
    }

}