package com.example.cryptoassets.infrastructure.dao

import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.repository.AtivoCarteiraRepository
import java.util.*

class AtivoCarteiraDAO(private val dbHelper : DbHelper) : AtivoCarteiraRepository {

    override fun ativos(): Set<AtivoCarteira> {
        return Collections.emptySet()
    }


}