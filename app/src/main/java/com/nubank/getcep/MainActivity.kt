package com.nubank.getcep

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ActionProvider.VisibilityListener
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.nubank.getcep.databinding.ActivityMainBinding
import com.nubank.getcep.model.Cep
import com.nubank.getcep.service.Cep.CepService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.zip.Inflater
import kotlin.coroutines.CoroutineContext

class MainActivity: AppCompatActivity() {
    lateinit var cepService: CepService
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConsulta.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            binding.cep.text = "Cep: "
            binding.logradouro.text = "Logradouro: "
            binding.complemento.text =  "Complemento: "
            binding.bairro.text = "Bairro; "
            binding.localidade.text = "Localidade: "
            binding.uf.text = "UF: "

            cepService = CepService()
            cepService.getInformations(binding.inputCep.text.toString())
            cepService.responseContainer.observe(this, Observer {
                if (it?.cep != null) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.cep.text = binding.cep.text.toString() + it.cep
                        binding.logradouro.text = binding.logradouro.text.toString() + it.logradouro
                        binding.complemento.text = binding.complemento.text.toString() + it.complemento
                        binding.bairro.text = binding.bairro.text.toString() + it.bairro
                        binding.localidade.text = binding.localidade.text.toString() + it.localidade
                        binding.uf.text = binding.uf.text.toString() + it.uf
                        binding.error.text = ""
                        binding.loading.visibility = View.INVISIBLE
                    }, 500)

                } else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.error.text = "Não foi possivel obter os dados deste cep: ${binding.inputCep.text.toString()}"
                        binding.loading.visibility = View.INVISIBLE
                    }, 500)
                }
            })
        }

    }
}