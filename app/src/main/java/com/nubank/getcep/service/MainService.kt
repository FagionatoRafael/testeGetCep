package com.nubank.getcep.service

import com.nubank.getcep.model.CepInterface
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainService() {

    val service: CepInterface
        get() {
            return Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(CepInterface::class.java)
        }
}