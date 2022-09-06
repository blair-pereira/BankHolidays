package com.example.ukbankholidays.di

import com.example.ukbankholidays.api.ApiDetails
import com.example.ukbankholidays.api.ApiReference
import com.example.ukbankholidays.repo.Repository
import com.example.ukbankholidays.repo.RepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGson():Gson{
        return Gson()
    }

    @Provides
    fun httpLoggingInterceptor():HttpLoggingInterceptor=
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient=
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()

    @Provides
    fun retrofitBuilder(gson: Gson,okHttpClient: OkHttpClient):Retrofit=
        Retrofit.Builder()
            .baseUrl(ApiReference.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun getApiDetails(retrofit: Retrofit):ApiDetails{
        return retrofit.create(ApiDetails::class.java)
    }

    @Provides
    fun getRepository(apiDetails: ApiDetails):Repository{
        return RepositoryImpl(apiDetails)

    }
}