package com.example.cryptoassets.core.exception

class FalhaConexaoException : RuntimeException {

    constructor(message: String, cause:Throwable):super(message, cause)
    internal constructor(message: String):super(message)

}