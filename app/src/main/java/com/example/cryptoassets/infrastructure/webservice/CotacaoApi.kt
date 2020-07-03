package com.example.cryptoassets.infrastructure.webservice

import android.content.Context
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.core.exception.ConexaoDesabilitadaException
import com.example.cryptoassets.core.exception.FalhaConexaoException
import com.example.cryptoassets.core.repository.CotacaoRepository
import com.example.cryptoassets.util.MoneyUtils
import com.example.cryptoassets.infrastructure.util.ConnectionUtils
import com.example.cryptoassets.infrastructure.webservice.client.MercadoBitcoinApiClient

class CotacaoApi(private val context:Context, private val apiClient: MercadoBitcoinApiClient) : CotacaoRepository {

    override fun cotacoes(): Set<Cotacao> {
        if(!ConnectionUtils.hasConnection(context)){
            throw ConexaoDesabilitadaException("Permissões de conexão com a internet desabilitadas")
        }
        return try {
            Ticker.values().map { ticker ->
                val result = apiClient.buscarResumoOperacoes(ticker.name).execute()
                val body = result.body()
                if (body?.last != null)
                    Cotacao(
                        ticker,
                        MoneyUtils.of(body.last)
                    )
                else
                    null
            }.filterNotNull().toSet()
        }
        catch(ex: Exception){
            throw FalhaConexaoException("Falha ao buscar cotacoes", ex)
        }
    }
}