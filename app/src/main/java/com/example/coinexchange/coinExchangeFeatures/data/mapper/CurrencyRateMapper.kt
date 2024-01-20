package com.example.coinexchange.coinExchangeFeatures.data.mapper

import com.example.coinexchange.coinExchangeFeatures.data.local.entity.CurrencyRateEntity
import com.example.coinexchange.coinExchangeFeatures.domain.model.CurrencyRate

fun CurrencyRateEntity.toCurrencyRate(): CurrencyRate{
    return CurrencyRate(
        code = code,
        name = name,
        rate = rate
    )
}


fun CurrencyRate.toCurrencyRateEntity(): CurrencyRateEntity{
    return CurrencyRateEntity(
        code = code,
        name = name,
        rate = rate
    )
}