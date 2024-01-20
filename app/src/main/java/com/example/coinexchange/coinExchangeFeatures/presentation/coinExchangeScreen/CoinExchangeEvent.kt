package com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen

sealed class CoinExchangeEvent {

    data object FromCurrencySelect: CoinExchangeEvent()
    data object ToCurrencySelect: CoinExchangeEvent()
    data object SwapButtonClick: CoinExchangeEvent()
    data class BottomSheetItemClick(val value: String): CoinExchangeEvent()
    data class NumberButtonClick(val value: String): CoinExchangeEvent()

}