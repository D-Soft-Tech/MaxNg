package com.example.maxng.di

import android.content.Context
import androidx.room.Room
import com.example.maxng.BuildConfig
import com.example.maxng.api.ApiService
import com.example.maxng.constants.AppConstants.BASE_URL
import com.example.maxng.localDB.LocalDao
import com.example.maxng.localDB.LocalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    PROVISION OF RETROFIT STARTS HERE
    @Singleton
    @Provides
    fun providesBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun providesOKHTTPClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okhttp: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okhttp)
            .build()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    // PROVISION OF ROOM DATABASE STARTS HERE
    @Singleton
    @Provides
    fun providesLocalDB(@ApplicationContext appContext: Context): LocalDataBase =
        Room.databaseBuilder(
            appContext,
            LocalDataBase::class.java,
            "MaxNG"
        ).build()

    @Singleton
    @Provides
    fun providesLocalDao(localDB: LocalDataBase): LocalDao =
        localDB.getLocalDao()
}
