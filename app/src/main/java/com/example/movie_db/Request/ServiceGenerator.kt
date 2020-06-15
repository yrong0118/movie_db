package com.example.movie_db.Request

import android.provider.SyncStateContract
import com.example.movie_db.Util.Constants
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object {
        private val retrofitBuilder :Retrofit.Builder =
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
        private val retrofit : Retrofit = retrofitBuilder.build()
        private val movieApi = retrofit.create(MovieApi::class.java)
        fun getMovieApi() : MovieApi {
            return movieApi
        }

    }

//    companion object {
//
//        val instance : Retrofit
//        init {
//            instance = Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(getHttpClient)
//                .build()
//        }
//
//        val getHttpClient: OkHttpClient
//            get() {
//                val loggingInterceptor = HttpLoggingInterceptor()
//                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//                var builder: OkHttpClient.Builder = OkHttpClient.Builder()
//                    .addInterceptor(MvoiesInterceptor())
//                    .addInterceptor(loggingInterceptor)
//                    .connectTimeout(10, TimeUnit.SECONDS)
//                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .readTimeout(10, TimeUnit.SECONDS)
//                return builder.build()
//            }
//
//
//        private class MvoiesInterceptor : Interceptor {
//            @Throws(IOException::class)
//            override fun intercept(chain: Interceptor.Chain): Response { //To change body of created functions use File | Settings | File Templates.
//                var original: Request = chain.request()
//                var request: Request = original
//                    .newBuilder()
//                    .build()
//                return chain.proceed(request)
//            }
//        }
//    }
 }