package com.example.cryptoassets.core.repository

import com.example.cryptoassets.core.domain.AtivoCarteira

interface AtivoCarteiraRepository {

    fun ativos(): Set<AtivoCarteira>

}