package com.example.cryptoassets.infrastructure.dao

import android.content.ContentValues
import android.database.Cursor
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.core.repository.AtivoRepository

class AtivoDAO(private val dbHelper : DbHelper) : AtivoRepository {

    override fun ativos(): Set<Ativo> {
        val db = dbHelper.writableDatabase
        val  sql = "SELECT * FROM $TABLE_ATIVO WHERE $ATIVO_EXCLUIDO <> 1 ORDER BY $ATIVO_NOME ASC"
        val cursor = db.rawQuery(sql ,null)
        val ativos =ArrayList<Ativo>()
        while (cursor.moveToNext()){
            val ativo= ativoFromCursor(cursor)
            ativos.add(ativo)
        }
        cursor.close()
        db.close()
        return ativos.toSet()
    }

    override fun salvar(ativo: Ativo) {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(ATIVO_TICKER, ativo.ticker.name)
            put(ATIVO_NOME, ativo.nome)
        }

        db.insert(TABLE_ATIVO, null, contentValues)
        db.close()
    }

    override fun excluir(ativo: Ativo) {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(ATIVO_EXCLUIDO, 1)
        }
        val selection = "$ATIVO_TICKER LIKE ?"
        val selectionArgs = arrayOf(ativo.ticker.name)
        db.update(TABLE_ATIVO, contentValues, selection, selectionArgs)
        db.close()
    }

    override fun findByTicker(ticker: Ticker): Ativo? {
        val db = dbHelper.writableDatabase
        val  sql = "SELECT * FROM $TABLE_ATIVO WHERE $ATIVO_TICKER LIKE ? AND $ATIVO_EXCLUIDO <> 1 ORDER BY $ATIVO_NOME ASC"
        val selectionArgs = arrayOf(ticker.name)
        val cursor = db.rawQuery(sql , selectionArgs)
        val ativo: Ativo? = if (cursor.moveToFirst()) ativoFromCursor(cursor) else null
        cursor.close()
        db.close()
        return ativo
    }

    private fun ativoFromCursor(cursor: Cursor): Ativo {
        val nome = cursor.getString(cursor.getColumnIndex(ATIVO_NOME))
        val codigoTicker  = cursor.getString(cursor.getColumnIndex(ATIVO_TICKER))
        val id = cursor.getInt(cursor.getColumnIndex(ATIVO_ID))
        val ticker = Ticker.criar(codigoTicker)!!
        return Ativo(
            id,
            ticker,
            nome
        )
    }

}