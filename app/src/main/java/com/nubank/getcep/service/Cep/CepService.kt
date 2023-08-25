package com.nubank.getcep.service.Cep

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nubank.getcep.model.Cep
import com.nubank.getcep.service.MainService
import kotlinx.coroutines.awaitAll
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class CepService {
    private var mainService: MainService = MainService()
    val responseContainer = MutableLiveData<Cep>()


    fun getInformations(cep: String): Cep? {
        mainService.service.getInformations(cep).enqueue(object: Callback<Cep> {

            /* The HTTP call failed. This method is run on the main thread */
            override fun onFailure(call: Call<Cep>, t: Throwable) {
                Log.d("TAG_", "An error happened!")
                t.printStackTrace()
            }

            /* The HTTP call was successful, we should still check status code and response body
             * on a production app. This method is run on the main thread */
             override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                /* This will print the response of the network call to the Logcat */
                if (response.isSuccessful) {
                    responseContainer.postValue(response.body())
                }
            }

        })
        return responseContainer.value
    }


}