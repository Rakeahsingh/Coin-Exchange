package com.example.coinexchange.coinExchangeFeatures.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.coinexchange.coinExchangeFeatures.data.local.entity.CurrencyRateEntity


@Dao
interface CurrencyDao {

    @Upsert
    suspend fun upsertAll(currencyRate: List<CurrencyRateEntity>)

    @Query("select * from currencyrateentity")
    suspend fun getAllCurrency(): List<CurrencyRateEntity>

}