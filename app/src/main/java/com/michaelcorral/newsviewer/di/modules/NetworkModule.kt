package com.michaelcorral.newsviewer.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.michaelcorral.newsviewer.BuildConfig
import com.michaelcorral.newsviewer.BuildConfig.api_key
import com.michaelcorral.newsviewer.api.services.NewsService
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


private const val CONNECT_TIMEOUT: Long = 20
private const val READ_TIMEOUT: Long = 20

private const val AUTHORIZATION: String = "Authorization"
private const val BEARER: String = "Bearer"

val networkModule = module {
    single { provideNewsService(get()) }
    single { provideRetrofit(get(), get(), get()) }
    single { provideOkHttpClient() }
    single { provideKotlinxSerializationConverterFactory() }
    single { provideRxJava2CallAdapterFactory() }
}

private fun provideNewsService(retrofit: Retrofit): NewsService =
    retrofit.create(NewsService::class.java)

private fun provideRetrofit(
    client: OkHttpClient,
    kotlinxSerializationConverterFactory: Converter.Factory,
    adapterFactory: RxJava2CallAdapterFactory
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.news_base_api)
    .addConverterFactory(kotlinxSerializationConverterFactory)
    .addCallAdapterFactory(adapterFactory)
    .client(client)
    .build()

private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    .addInterceptor {
        chain -> val newRequest: Request = chain.request().newBuilder()
        .addHeader(AUTHORIZATION, "$BEARER $api_key")
        .build()
        chain.proceed(newRequest)
    }
    .build()

private fun provideKotlinxSerializationConverterFactory(): Converter.Factory
= Json.asConverterFactory(provideContentType())

private fun provideContentType(): MediaType = MediaType.get("application/json")

private fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
    RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())