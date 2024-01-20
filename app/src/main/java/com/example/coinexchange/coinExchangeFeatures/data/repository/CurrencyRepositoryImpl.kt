package com.example.coinexchange.coinExchangeFeatures.data.repository

import com.example.coinexchange.coinExchangeFeatures.data.local.CurrencyDao
import com.example.coinexchange.coinExchangeFeatures.data.mapper.toCurrencyRate
import com.example.coinexchange.coinExchangeFeatures.data.mapper.toCurrencyRateEntity
import com.example.coinexchange.coinExchangeFeatures.data.remote.CurrencyApi
import com.example.coinexchange.coinExchangeFeatures.domain.model.CurrencyRate
import com.example.coinexchange.coinExchangeFeatures.domain.repository.CurrencyRepository
import com.example.coinexchange.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CurrencyRepositoryImpl(
    private val api: CurrencyApi,
    private val dao: CurrencyDao
): CurrencyRepository {
    override fun getCurrencyRate(): Flow<Resources<List<CurrencyRate>>> {
        return flow {
            val localCurrencyRate = dao.getAllCurrency().map {
                it.toCurrencyRate()
            }
            emit(Resources.Success(localCurrencyRate))

            try {

                val newRates =  api.getCurrency().toCurrencyRate()
                dao.upsertAll(newRates.map { it.toCurrencyRateEntity() })
                emit(Resources.Success(newRates))

            }catch (e: HttpException){
                emit(Resources.Error(
                    data = localCurrencyRate,
                    message = "Something Went wrong check your network connection!"
                )
                )
            }catch (e: IOException){
               emit(Resources.Error(
                    data = localCurrencyRate,
                    message = "Error Found?"
                )
               )
            }
        }
    }

}