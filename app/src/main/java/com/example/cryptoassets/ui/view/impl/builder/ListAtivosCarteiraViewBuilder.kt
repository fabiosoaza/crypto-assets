package com.example.cryptoassets.ui.view.impl.builder

import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.Carteira
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.ui.interactor.ListAtivosCarteiraInteractor
import com.example.cryptoassets.ui.view.ListAtivosCarteiraView
import com.example.cryptoassets.ui.view.impl.ListAtivosCarteiraViewImpl

class ListAtivosCarteiraViewBuilder {

    private var cotacoes : MutableList<Cotacao> = mutableListOf<Cotacao>()
    private var ativoCarteiras : MutableList<AtivoCarteira> = mutableListOf<AtivoCarteira>()

    fun cotacoes(cotacoes: MutableList<Cotacao>) = apply { this.cotacoes = cotacoes }

    fun ativos(ativoCarteiras: MutableList<AtivoCarteira>) = apply {
        this.ativoCarteiras = ativoCarteiras
    }

    fun build(): ListAtivosCarteiraView {
        val carteira = Carteira(ativoCarteiras)
        val iteractor =
            ListAtivosCarteiraInteractor(
                carteira,
                cotacoes
            )
        return ListAtivosCarteiraViewImpl(iteractor)
    }

}