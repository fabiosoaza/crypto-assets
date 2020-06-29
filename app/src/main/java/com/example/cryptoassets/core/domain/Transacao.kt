package com.example.cryptoassets.core.domain

import java.time.LocalDateTime

data class Transacao(val id:Int?, val ativo:AtivoCarteira, val data:LocalDateTime, val tipo:TipoTransacao) {
    constructor(ativo:AtivoCarteira, data:LocalDateTime, tipo:TipoTransacao) : this(null, ativo, data, tipo)

}