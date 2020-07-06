package com.example.cryptoassets.context

import android.content.Context
import com.example.cryptoassets.BuildConfig
import com.example.cryptoassets.core.repository.AtivoCarteiraRepository
import com.example.cryptoassets.core.repository.AtivoRepository
import com.example.cryptoassets.core.repository.CotacaoRepository
import com.example.cryptoassets.core.repository.TransacaoRepository
import com.example.cryptoassets.infrastructure.dao.AtivoCarteiraDAO
import com.example.cryptoassets.infrastructure.dao.AtivoDAO
import com.example.cryptoassets.infrastructure.dao.DbHelper
import com.example.cryptoassets.infrastructure.dao.TransacaoDAO
import com.example.cryptoassets.infrastructure.webservice.CotacaoApi
import com.example.cryptoassets.infrastructure.webservice.client.MercadoBitcoinApiClient
import com.example.cryptoassets.core.interactor.AtivoInteractor
import com.example.cryptoassets.core.interactor.BuscaCotacaoInteractor
import com.example.cryptoassets.core.interactor.TransacaoInteractor
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationComponentsContext(private val context: Context) {


    private val ativoDAO = AtivoDAO(dbHelper())
    private val transacaoDAO = TransacaoDAO(dbHelper())
    private val ativoCarteiraDAO = AtivoCarteiraDAO(dbHelper())
    private val cotacaoApi = CotacaoApi(context, mercadoBitcoinApiClient())
    private val ativoInteractor = AtivoInteractor(context, ativoRepository(), transacaoRepository(), ativoCarteiraRepository())
    private val transacaoInteractor = TransacaoInteractor(
        context,
        transacaoRepository(),
        ativoRepository(),
        ativoCarteiraRepository()
    )
    private val buscaCotacaoInteractor = BuscaCotacaoInteractor(context, cotacaoRepository())


    fun ativoRepository(): AtivoRepository {
        return ativoDAO
    }

    fun transacaoRepository(): TransacaoRepository {
        return transacaoDAO
    }

    fun ativoCarteiraRepository(): AtivoCarteiraRepository {
        return ativoCarteiraDAO
    }

    fun cotacaoRepository(): CotacaoRepository {
        return cotacaoApi
    }


    fun ativoInteractor(): AtivoInteractor {
        return ativoInteractor
    }

    fun transacaoInteractor(): TransacaoInteractor {
        return transacaoInteractor
    }

    fun buscaCotacaoInteractor():BuscaCotacaoInteractor{
        return buscaCotacaoInteractor
    }

    private fun dbHelper(): DbHelper {
        return DbHelper(context)
    }

    private fun mercadoBitcoinApiClient(): MercadoBitcoinApiClient {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerModule(KotlinModule())
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(6, TimeUnit.SECONDS)
            .connectTimeout(6, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MERCADO_BITCOIN_API_URL)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(okHttpClient)
            .build()

        return retrofit.create(MercadoBitcoinApiClient::class.java)
    }


}