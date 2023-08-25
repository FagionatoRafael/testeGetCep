package com.nubank.getcep.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepInterface {

    @GET("{cep}/json/")
    fun getInformations(@Path("cep") cep: String): Call<Cep>
}