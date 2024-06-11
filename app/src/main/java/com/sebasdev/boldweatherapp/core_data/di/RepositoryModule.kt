package com.sebasdev.boldweatherapp.core_data.di

import com.sebasdev.boldweatherapp.core_data.data_source.local.WeatherLocalDataSource
import com.sebasdev.boldweatherapp.core_data.data_source.local.WeatherLocalDataSourceImpl
import com.sebasdev.boldweatherapp.core_data.data_source.remote.WeatherRemoteDataSource
import com.sebasdev.boldweatherapp.core_data.data_source.remote.WeatherRemoteDataSourceImpl
import com.sebasdev.boldweatherapp.core_data.repository.WeatherRepositoryImpl
import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRemoteDataSource(
        weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl
    ): WeatherRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindWeatherLocalDataSource(
        weatherLocalDataSourceImpl: WeatherLocalDataSourceImpl
    ): WeatherLocalDataSource

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}