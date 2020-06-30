package com.example.cryptoassets.infrastructure.webservice

import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.core.repository.CotacaoRepository
import java.util.*

class CotacaoApi : CotacaoRepository {

    override fun cotacoes(): Set<Cotacao> {
        return Collections.emptySet()
    }
}