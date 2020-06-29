package com.example.cryptoassets.core.domain

enum class TipoTransacao(val codigo:Int) {

    COMPRA(1), VENDA(2);

    companion object {
        fun criar(codigo : Int) : TipoTransacao? {
            return TipoTransacao.values().filter{ tipo -> tipo.codigo == codigo}[0]
        }
    }


}