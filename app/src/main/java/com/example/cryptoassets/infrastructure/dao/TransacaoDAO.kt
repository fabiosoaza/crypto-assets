package com.example.cryptoassets.infrastructure.dao

import android.content.ContentValues
import android.database.Cursor
import com.example.cryptoassets.core.domain.*
import com.example.cryptoassets.core.repository.TransacaoRepository
import com.example.cryptoassets.core.util.BigDecimalUtils
import com.example.cryptoassets.core.util.MoneyUtils
import org.javamoney.moneta.CurrencyUnitBuilder
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.money.CurrencyUnit

class TransacaoDAO(private val dbHelper : DbHelper) : TransacaoRepository {

    override fun transacoes(): Set<Transacao> {

        val db = dbHelper.writableDatabase
        val  sql = "SELECT * FROM $TABLE_TRANSACAO AS t INNER JOIN $TABLE_ATIVO AS a ON t.$TRANSACAO_ATIVO_ID = a.$ATIVO_ID ORDER BY $TRANSACAO_DATA ASC"
        val cursor = db.rawQuery(sql ,null)
        val transacaos =ArrayList<Transacao>()
        while (cursor.moveToNext()){
            val transacao= transacaoFromCursor(cursor)
            transacaos.add(transacao)
        }
        cursor.close()
        db.close()
        return transacaos.toSet()
    }

    override fun salvar(transacao: Transacao) {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(TRANSACAO_ATIVO_ID, transacao.ativo.ativo.id)
            put(TRANSACAO_DATA, transacao.data.atZone(ZoneOffset.UTC).toEpochSecond())
            put(TRANSACAO_TIPO, transacao.tipo.codigo)
            put(TRANSACAO_QUANTIDADE, transacao.ativo.quantidade.toDouble())
            put(TRANSACAO_PRECO_MEDIO, transacao.ativo.precoMedio.number.toDouble())
            put(TRANSACAO_CODIGO_MOEDA, transacao.ativo.precoMedio.currency.currencyCode)
        }
        db.insert(TABLE_TRANSACAO, null, contentValues)
        db.close()
    }

    override fun excluir(transacao: Transacao) {

    }



    private fun transacaoFromCursor(cursor: Cursor): Transacao {
        val id = cursor.getInt(cursor.getColumnIndex(TRANSACAO_ID))
        val ativoId  = cursor.getInt(cursor.getColumnIndex(TRANSACAO_ATIVO_ID))
        val codigoMoeda  = cursor.getString(cursor.getColumnIndex(TRANSACAO_CODIGO_MOEDA))
        val nomeAtivo  = cursor.getString(cursor.getColumnIndex(ATIVO_NOME))
        val tickerAtivo  = cursor.getString(cursor.getColumnIndex(ATIVO_TICKER))
        val timestampTransacao = cursor.getLong(cursor.getColumnIndex(TRANSACAO_DATA))
        val tipo = cursor.getInt(cursor.getColumnIndex(TRANSACAO_DATA))
        val quantidade = cursor.getDouble(cursor.getColumnIndex(TRANSACAO_QUANTIDADE))
        val precoMedio = cursor.getDouble(cursor.getColumnIndex(TRANSACAO_PRECO_MEDIO))
        val ticker = Ticker.criar(tickerAtivo)!!
        val tipoTransacao = TipoTransacao.criar(tipo)!!
        val moeda = MoneyUtils.getCurrency(codigoMoeda)
        val preco = MoneyUtils.of(precoMedio, moeda)
        val data = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestampTransacao), ZoneOffset.UTC)
        val ativoCarteira = AtivoCarteira(Ativo(ativoId, ticker, nomeAtivo ), BigDecimalUtils.ofDouble(quantidade), preco)
        return Transacao(id, ativoCarteira, data, tipoTransacao)




    }

}