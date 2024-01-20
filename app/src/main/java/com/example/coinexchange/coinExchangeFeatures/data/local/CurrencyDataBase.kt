package com.example.coinexchange.coinExchangeFeatures.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coinexchange.coinExchangeFeatures.data.local.entity.CurrencyRateEntity


@Database(
    entities = [CurrencyRateEntity::class],
    version = 1,
    exportSchema = false
    )
abstract class CurrencyDataBase: RoomDatabase() {

    abstract val dao: CurrencyDao

}