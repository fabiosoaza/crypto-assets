package com.example.cryptoassets.infrastructure.webservice.client

import com.example.cryptoassets.infrastructure.webservice.client.dto.TickerDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MercadoBitcoinApiClient {

    @GET("{coin}/ticker/")
    fun buscarResumoOperacoes(@Path("coin") coin : String): Call<TickerDTO>
}