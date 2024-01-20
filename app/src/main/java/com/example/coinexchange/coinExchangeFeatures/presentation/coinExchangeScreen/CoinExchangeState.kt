package com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen

import com.example.coinexchange.coinExchangeFeatures.domain.model.CurrencyRate

data class CoinExchangeState(
    val fromCurrencyCode: String = "INR",
    val toCurrencyCode: String = "USD",
    val fromCurrencyValue: String = "0.00",
    val toCurrencyValue: String = "0.00",
    val selection: SelectionState = SelectionState.FROM,
    val currencyRates: Map<String, CurrencyRate> = emptyMap(),
    val error: String? = null
)


enum class SelectionState{
    FROM,
    To
}
