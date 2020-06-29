package com.example.cryptoassets.configuration

import android.content.Context
import com.example.cryptoassets.core.repository.AtivoRepository
import com.example.cryptoassets.core.repository.TransacaoRepository
import com.example.cryptoassets.infrastructure.dao.AtivoDAO
import com.example.cryptoassets.infrastructure.dao.DbHelper
import com.example.cryptoassets.infrastructure.dao.TransacaoDAO

class BeansFactory(private val context:Context) {

    fun ativoRepository():AtivoRepository{
        return AtivoDAO(dbHelper())
    }

    fun transacaoRepository(): TransacaoRepository {
        return TransacaoDAO(dbHelper())
    }

    fun dbHelper() : DbHelper{
        return DbHelper(context)
    }

}