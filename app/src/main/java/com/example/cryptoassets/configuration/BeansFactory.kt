package com.example.cryptoassets.configuration

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
import com.example.cryptoassets.ui.interactor.AtivoInteractor
import com.example.cryptoassets.ui.interactor.TransacaoInteractor
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

class BeansFactory(private val context:Context) {


    fun ativoRepository():AtivoRepository{
        return AtivoDAO(dbHelper())
    }

    fun transacaoRepository(): TransacaoRepository {
        return TransacaoDAO(dbHelper())
    }

    fun ativoCarteiraRepository(): AtivoCarteiraRepository {
        return AtivoCarteiraDAO(dbHelper())
    }

    fun cotacaoRepository():CotacaoRepository{
        return CotacaoApi(context, mercadoBitcoinApiClient())
    }

    fun ativoInteractor() : AtivoInteractor {
        return AtivoInteractor(ativoRepository(), context)
    }

    fun transacaoInteractor():TransacaoInteractor{
        return TransacaoInteractor(
            context,
            transacaoRepository(),
            ativoRepository(),
            ativoCarteiraRepository()
        )
    }

    fun dbHelper() : DbHelper{
        return DbHelper(context)
    }

    private fun mercadoBitcoinApiClient() : MercadoBitcoinApiClient{
        val objectMapper  = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerModule( KotlinModule())
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