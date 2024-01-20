package com.example.coinexchange.coinExchangeFeatures.data.remote

import com.example.coinexchange.coinExchangeFeatures.data.remote.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/v1/latest")
    suspend fun getCurrency(
        @Query("apikey") apikey: String = API_KEY
    ): CurrencyDto


    companion object{
        const val API_KEY = "p72HnN2uDUUHEbh2BEuFgKQRd9pkqutaETfvLoG3"
        const val BASE_URL = "https://api.freecurrencyapi.com/"
    }

}