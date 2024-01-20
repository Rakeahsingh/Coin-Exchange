package com.example.coinexchange.coinExchangeFeatures.domain.repository

import com.example.coinexchange.coinExchangeFeatures.domain.model.CurrencyRate
import com.example.coinexchange.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getCurrencyRate(): Flow<Resources<List<CurrencyRate>>>

}