package com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinexchange.coinExchangeFeatures.domain.use_case.GetCurrencyRateUseCase
import com.example.coinexchange.core.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CoinExchangeViewModel@Inject constructor(
    private val currencyRateUseCase: GetCurrencyRateUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CoinExchangeState())
    val state = _state.asStateFlow()

    init {
        getCurrencyRates()
    }

    fun onEvent(event: CoinExchangeEvent){
        when(event){
            is CoinExchangeEvent.BottomSheetItemClick -> {
                if (_state.value.selection == SelectionState.FROM){
                    _state.update { it.copy(
                        fromCurrencyCode = event.value
                    ) }
                }
                if (_state.value.selection == SelectionState.To){
                    _state.update { it.copy(
                        toCurrencyCode = event.value
                    ) }
                }
                updateCurrencyValue("")
            }
            is CoinExchangeEvent.FromCurrencySelect -> {
                _state.update { it.copy(selection = SelectionState.FROM) }
            }
            is CoinExchangeEvent.NumberButtonClick -> {
                updateCurrencyValue(value = event.value)
            }
            is CoinExchangeEvent.SwapButtonClick -> {
                _state.update { it.copy(
                    fromCurrencyCode = _state.value.toCurrencyCode,
                    toCurrencyCode = _state.value.fromCurrencyCode,
                    fromCurrencyValue = _state.value.toCurrencyValue,
                    toCurrencyValue = _state.value.fromCurrencyValue
                ) }
            }
            is CoinExchangeEvent.ToCurrencySelect -> {
                _state.update { it.copy(selection = SelectionState.To) }
            }
        }
    }

    private fun updateCurrencyValue(value: String) {
        val currentCurrencyValue = when(_state.value.selection){
            SelectionState.FROM -> _state.value.fromCurrencyValue
            SelectionState.To -> _state.value.toCurrencyValue
        }

        val fromCurrencyRate = _state.value.currencyRates[_state.value.fromCurrencyCode]?.rate ?: 0.0
        val toCurrencyRate = _state.value.currencyRates[_state.value.toCurrencyCode]?.rate ?: 0.0

        val updatedCurrencyValue = when(value){
            "C" -> "0.00"
            else -> if (currentCurrencyValue == "0.00") value else currentCurrencyValue + value
        }

        val newNumberFormat = DecimalFormat("#.00")

        when(_state.value.selection){
            SelectionState.FROM -> {
                val fromValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                val toValue = fromValue / fromCurrencyRate * toCurrencyRate
                _state.update { it.copy(
                    fromCurrencyValue = updatedCurrencyValue,
                    toCurrencyValue = newNumberFormat.format(toValue)
                ) }
            }
            SelectionState.To -> {
                val toValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                val fromValue = toValue / toCurrencyRate * fromCurrencyRate
                _state.update { it.copy(
                    fromCurrencyValue = updatedCurrencyValue,
                    toCurrencyValue = newNumberFormat.format(fromValue)
                ) }
            }
        }

    }

    private fun getCurrencyRates(){
        viewModelScope.launch {
            currencyRateUseCase
                .invoke().collectLatest{ result ->
                    when(result){
                        is Resources.Error -> {
                            _state.update {
                                it.copy(
                                    currencyRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                    error = result.message
                                )
                            }
                        }
                        is Resources.Loading -> {
                            _state.update {
                                it.copy(
                                    currencyRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                    error = null
                                )
                            }
                        }
                        is Resources.Success -> {
                            _state.update {
                                it.copy(
                                    currencyRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                    error = null
                                )
                            }
                        }
                    }
                }

        }
    }

}