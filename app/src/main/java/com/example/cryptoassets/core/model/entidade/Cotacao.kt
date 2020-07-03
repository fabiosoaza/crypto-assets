package com.example.cryptoassets.core.model.entidade

import org.javamoney.moneta.Money

data class Cotacao(val ticker: Ticker, val valor:Money) {
}