package com.sebasdev.boldweatherapp.core_data.di

import android.content.Context
import androidx.room.Room
import com.sebasdev.boldweatherapp.core_data.data_source.local.db.WeatherDataBase
import com.sebasdev.boldweatherapp.core_data.data_source.remote.api.WeatherApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {

    @Provides
    @Singleton
    fun provideWeatherDataBase(@ApplicationContext appContext: Context): WeatherDataBase {
        return Room.databaseBuilder(
            appContext,
            WeatherDataBase::class.java, WeatherDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}