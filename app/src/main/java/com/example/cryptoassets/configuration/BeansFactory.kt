package com.example.cryptoassets.configuration

import android.content.Context
import com.example.cryptoassets.core.repository.AtivoCarteiraRepository
import com.example.cryptoassets.core.repository.AtivoRepository
import com.example.cryptoassets.core.repository.CotacaoRepository
import com.example.cryptoassets.core.repository.TransacaoRepository
import com.example.cryptoassets.infrastructure.dao.AtivoCarteiraDAO
import com.example.cryptoassets.infrastructure.dao.AtivoDAO
import com.example.cryptoassets.infrastructure.dao.DbHelper
import com.example.cryptoassets.infrastructure.dao.TransacaoDAO
import com.example.cryptoassets.infrastructure.webservice.CotacaoApi
import com.example.cryptoassets.ui.interactor.AtivoInteractor
import com.example.cryptoassets.ui.interactor.TransacaoInteractor

class BeansFactory(private val context:Context) {

    fun dbHelper() : DbHelper{
        return DbHelper(context)
    }

    fun ativoRepository():AtivoRepository{
        return AtivoDAO(dbHelper())
    }

    fun transacaoRepository(): TransacaoRepository {
        return TransacaoDAO(dbHelper())
    }

    fun ativoCarteiraRepository(): AtivoCarteiraRepository {
        return AtivoCarteiraDAO(dbHelper())
    }

    fun cotacaoRepository():CotacaoRepository{
        return CotacaoApi()
    }

    fun ativoInteractor() : AtivoInteractor {
        return AtivoInteractor(ativoRepository(), context)
    }

    fun transacaoInteractor():TransacaoInteractor{
        return TransacaoInteractor(
            transacaoRepository(),
            ativoRepository(),
            context
        )
    }

}