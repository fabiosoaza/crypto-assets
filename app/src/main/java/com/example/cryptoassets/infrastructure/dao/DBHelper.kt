package com.example.cryptoassets.infrastructure.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        criarTabelaAtivo(db)
        criarTabelaTransacao(db)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        dropTable(db, TABLE_ATIVO)
        dropTable(db, TABLE_TRANSACAO)
        onCreate(db)
    }


    private fun criarTabelaAtivo(db: SQLiteDatabase) {
        val sql = "CREATE TABLE $TABLE_ATIVO ($ATIVO_ID  INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, $ATIVO_NOME TEXT, $ATIVO_TICKER TEXT,$ATIVO_EXCLUIDO INTEGER NOT NULL DEFAULT 0)"
        db.execSQL(sql)
    }

    private fun criarTabelaTransacao(db: SQLiteDatabase) {
        val sql = "CREATE TABLE $TABLE_TRANSACAO ($TRANSACAO_ID  INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $TRANSACAO_ATIVO_ID INTEGER NOT NULL, $TRANSACAO_DATA INTEGER,$TRANSACAO_TIPO INTEGER, " +
                "$TRANSACAO_QUANTIDADE REAL, $TRANSACAO_PRECO_MEDIO REAL, $TRANSACAO_CODIGO_MOEDA TEXT)"
        db.execSQL(sql)
    }

    private fun dropTable(db: SQLiteDatabase, table: String) {
        db.execSQL("DROP TABLE IF EXISTS $table ")
    }
}