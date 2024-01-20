package com.example.coinexchange.coinExchangeFeatures.domain.use_case

import com.example.coinexchange.coinExchangeFeatures.domain.model.CurrencyRate
import com.example.coinexchange.coinExchangeFeatures.domain.repository.CurrencyRepository
import com.example.coinexchange.core.utils.Resources
import kotlinx.coroutines.flow.Flow

class GetCurrencyRateUseCase(
    private val repository: CurrencyRepository
) {

    operator fun invoke(): Flow<Resources<List<CurrencyRate>>>{
        return repository.getCurrencyRate()
    }

}