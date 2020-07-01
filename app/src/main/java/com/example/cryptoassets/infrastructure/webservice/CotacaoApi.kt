package com.example.cryptoassets.infrastructure.webservice

import android.content.Context
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.core.domain.Ticker
import com.example.cryptoassets.core.exception.ConexaoDesabilitadaException
import com.example.cryptoassets.core.exception.FalhaConexaoException
import com.example.cryptoassets.core.repository.CotacaoRepository
import com.example.cryptoassets.core.util.MoneyUtils
import com.example.cryptoassets.infrastructure.util.ConnectionUtils
import com.example.cryptoassets.infrastructure.webservice.client.MercadoBitcoinApiClient
import java.util.*

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
                    Cotacao(ticker, MoneyUtils.of(body.last))
                else
                    null
            }.filterNotNull().toSet()
        }
        catch(ex: Exception){
            throw FalhaConexaoException("Falha ao buscar cotacoes", ex)
        }
    }
}