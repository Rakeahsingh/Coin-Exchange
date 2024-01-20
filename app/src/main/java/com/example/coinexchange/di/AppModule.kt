package com.example.coinexchange.di

import android.app.Application
import androidx.room.Room
import com.example.coinexchange.coinExchangeFeatures.data.local.CurrencyDataBase
import com.example.coinexchange.coinExchangeFeatures.data.remote.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): CurrencyApi{
        return Retrofit.Builder()
            .baseUrl(CurrencyApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CurrencyDataBase{
        return Room.databaseBuilder(
           app,
            CurrencyDataBase::class.java,
            "currency_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}