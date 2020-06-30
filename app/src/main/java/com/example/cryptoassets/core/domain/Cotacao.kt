package com.example.cryptoassets.core.domain

import org.javamoney.moneta.Money

data class Cotacao(val ticker:Ticker, val valor:Money) {
}