package com.example.cryptoassets.core.domain

enum class Ticker(val tipo: TipoAtivo) {

    BTC(TipoAtivo.CRYPTO), LTC(TipoAtivo.CRYPTO);

    companion object {

        fun criar(codigo : String) : Ticker? {
            return Ticker.values().filter{ ticker -> ticker.name == codigo}[0]
        }
    }

}