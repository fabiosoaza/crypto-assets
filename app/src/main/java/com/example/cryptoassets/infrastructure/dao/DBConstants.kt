package com.example.cryptoassets.infrastructure.dao

val DATABASE_NAME = "carteira_ativos.db"
val DATABASE_VERSION = 1
val TABLE_ATIVO = "ativo"
val ATIVO_ID = "id"
val ATIVO_NOME = "nome"
val ATIVO_TICKER = "ticker"
val ATIVO_EXCLUIDO = "excluido"

val TABLE_TRANSACAO = "transacao"
val TRANSACAO_ID = "id"
val TRANSACAO_ATIVO_ID = "ativo_id"
val TRANSACAO_DATA = "data"
val TRANSACAO_TIPO= "tipo"
val TRANSACAO_QUANTIDADE= "quantidade"
val TRANSACAO_PRECO_MEDIO= "preco_medio"
val TRANSACAO_CODIGO_MOEDA= "moeda"