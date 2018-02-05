package com.example.rahul.autocoupons.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by rahul on 4/2/18.
 */
object ApiClient {
        @JvmStatic
        var BASE_URL = "https://s3-eu-west-1.amazonaws.com/forza-assignment/android/"

        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }
    }