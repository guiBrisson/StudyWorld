package me.brisson.studyworld.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.brisson.studyworld.data.api.repository.CountryRepositoryImpl
import me.brisson.studyworld.data.api.service.CountryService
import me.brisson.studyworld.domain.repository.CountryRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    private val baseUrl = "https://restcountries.com/v3.1/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Provides
    @Singleton
    fun providesApi(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesCountryService(retrofit: Retrofit): CountryService =
        retrofit.create(CountryService::class.java)

    @Provides
    @Singleton
    fun providesCountryRepository(countryService: CountryService): CountryRepository =
        CountryRepositoryImpl(countryService)

}