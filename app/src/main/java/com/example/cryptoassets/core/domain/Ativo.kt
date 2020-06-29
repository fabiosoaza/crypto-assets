package com.example.cryptoassets.core.domain

import java.util.*

data class Ativo(val id:Int?, val ticker: Ticker, val nome:String) {

    constructor (ticker: Ticker, nome:String) : this(null, ticker, nome)


    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Ativo) return false
        return Objects.equals(this.ticker, other.ticker)
    }

    override fun hashCode(): Int {
        return Objects.hashCode(ticker)
    }

}