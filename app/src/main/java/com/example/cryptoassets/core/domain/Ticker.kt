package com.example.cryptoassets.core.domain

enum class Ticker(val tipo: TipoAtivo) {

    BTC(TipoAtivo.CRYPTO),
    LTC(TipoAtivo.CRYPTO),
    BCH(TipoAtivo.CRYPTO),
    XRP(TipoAtivo.CRYPTO),
    ETH(TipoAtivo.CRYPTO),
    USDC(TipoAtivo.CRYPTO);

    companion object {

        fun criar(codigo : String) : Ticker? {
            return Ticker.values().toList().filter{ ticker -> ticker.name == codigo}.firstOrNull()
        }
    }

}