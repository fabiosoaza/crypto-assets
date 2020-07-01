package com.example.cryptoassets.infrastructure.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.cryptoassets.core.domain.Ativo
import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.Ticker
import com.example.cryptoassets.core.repository.AtivoCarteiraRepository
import com.example.cryptoassets.core.util.BigDecimalUtils
import com.example.cryptoassets.core.util.MoneyUtils
import java.util.*

class AtivoCarteiraDAO(private val dbHelper : DbHelper) : AtivoCarteiraRepository {

    override fun ativos(): Set<AtivoCarteira> {
        val db = dbHelper.writableDatabase
        val  sql = "SELECT c.*, a.$ATIVO_NOME, a.$ATIVO_TICKER FROM $TABLE_CARTEIRA AS c INNER JOIN $TABLE_ATIVO AS a ON c.$CARTEIRA_ATIVO_ID = a.$ATIVO_ID ORDER BY a.$ATIVO_NOME ASC"
        val cursor = db.rawQuery(sql ,null)
        val ativosCarteira =ArrayList<AtivoCarteira>()
        while (cursor.moveToNext()){
            val ativoCarteira= ativoCarteiraFromCursor(cursor)
            ativosCarteira.add(ativoCarteira)
        }
        cursor.close()
        db.close()
        return ativosCarteira.toSet()
    }

    override fun salvar(ativoCarteira: AtivoCarteira) {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(CARTEIRA_ID, ativoCarteira.ativo.id)
            put(CARTEIRA_ATIVO_ID, ativoCarteira.ativo.id)
            put(CARTEIRA_QUANTIDADE, ativoCarteira.quantidade.toDouble())
            put(CARTEIRA_PRECO_MEDIO, ativoCarteira.precoMedio.number.toDouble())
            put(CARTEIRA_CODIGO_MOEDA, ativoCarteira.precoMedio.currency.currencyCode)
        }
        db.insertWithOnConflict(TABLE_CARTEIRA, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    override fun buscar(ticker: Ticker): AtivoCarteira? {
        val db = dbHelper.writableDatabase
        val sql = "SELECT c.*, a.$ATIVO_NOME, a.$ATIVO_TICKER FROM $TABLE_CARTEIRA AS c INNER JOIN $TABLE_ATIVO AS a ON c.$CARTEIRA_ATIVO_ID = a.$ATIVO_ID " +
                    " WHERE a.$ATIVO_TICKER LIKE ? "
        val selectionArgs = arrayOf(ticker.name)
        val cursor = db.rawQuery(sql, selectionArgs)
        val ativo = if (cursor.moveToFirst()) ativoCarteiraFromCursor(cursor) else null
        cursor.close()
        db.close()
        return ativo
    }


    private fun ativoCarteiraFromCursor(cursor: Cursor): AtivoCarteira {
        val id = cursor.getInt(cursor.getColumnIndex(CARTEIRA_ID))
        val ativoId  = cursor.getInt(cursor.getColumnIndex(CARTEIRA_ATIVO_ID))
        val codigoMoeda  = cursor.getString(cursor.getColumnIndex(CARTEIRA_CODIGO_MOEDA))
        val nomeAtivo  = cursor.getString(cursor.getColumnIndex(ATIVO_NOME))
        val tickerAtivo  = cursor.getString(cursor.getColumnIndex(ATIVO_TICKER))

        val quantidade = cursor.getDouble(cursor.getColumnIndex(CARTEIRA_QUANTIDADE))
        val precoMedio = cursor.getDouble(cursor.getColumnIndex(CARTEIRA_PRECO_MEDIO))
        val ticker = Ticker.criar(tickerAtivo)!!

        val moeda = MoneyUtils.getCurrency(codigoMoeda)
        val preco = MoneyUtils.of(precoMedio, moeda)
        return AtivoCarteira(id, Ativo(ativoId, ticker, nomeAtivo ), BigDecimalUtils.ofDouble(quantidade), preco)
    }


}