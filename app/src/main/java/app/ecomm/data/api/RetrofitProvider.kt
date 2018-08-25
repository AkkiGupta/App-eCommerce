package app.ecomm.data.api

import android.app.Application
import app.ecomm.data.livedata.LiveDataCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import app.ecomm.data.api.intercepter.AnnotationCallAdapterFactory
import app.ecomm.data.util.NetworkConstants
import java.util.concurrent.TimeUnit

/**
 * Utility class to provide Retrofit and OkHttp resources.
 */

object RetrofitProvider {
    private val annotationRegistration = HashMap<Int, RequestPolicy>()
    private val showNetworkLogs: Boolean = false

    fun provideDefaultRetrofit(context: Application): Retrofit {
        val factories = arrayListOf<CallAdapter.Factory>()
        factories.add(LiveDataCallAdapterFactory())
//        factories.add(Retrofit2Platform.defaultCallAdapterFactory(null))

        return Retrofit.Builder()
                .baseUrl(NetworkConstants.dummyBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient(context))
                .addCallAdapterFactory(AnnotationCallAdapterFactory(factories, annotationRegistration))
                .build()
    }

    private fun provideOkHttpClient(context: Application): OkHttpClient {
        val cacheSize = 20 * 1024 * 1024L // 20 MB
        val cache = Cache(context.cacheDir, cacheSize)

        val builder = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
        if (showNetworkLogs) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
//        builder.addInterceptor(AnnotationInterceptor(annotationRegistration, context))
        builder.cache(cache)
        return builder.build()
    }
}
