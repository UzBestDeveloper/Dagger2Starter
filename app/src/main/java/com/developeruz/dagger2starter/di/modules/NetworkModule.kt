package com.developeruz.dagger2starter.di.modules

import com.developeruz.dagger2starter.BuildConfig
import com.developeruz.dagger2starter.di.scopes.NetworkMoshi
import com.developeruz.dagger2starter.network.api.AuthService
import com.developeruz.dagger2starter.utils.NullToBooleanAdapter
import com.developeruz.dagger2starter.utils.NullToEmptyStringAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =  retrofit.create()


    @Singleton
    @Provides
    fun provideRetrofit(factory: MoshiConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(factory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(@NetworkMoshi moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Singleton
    @NetworkMoshi
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(NullToBooleanAdapter())
            .add(NullToEmptyStringAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }


}