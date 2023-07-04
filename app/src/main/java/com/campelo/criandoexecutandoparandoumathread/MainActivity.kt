package com.campelo.criandoexecutandoparandoumathread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.campelo.criandoexecutandoparandoumathread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var pararThread = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnExecutar.setOnClickListener {
            minhaThread().start()
        }

        binding.btnParar.setOnClickListener {
            pararThread = true
            binding.btnExecutar.text = "Executar"
            binding.btnExecutar.isEnabled = true
        }
    }

    inner class minhaThread: Thread(){
        override fun run() {
            super.run()

            repeat(10){i ->
                if(pararThread){
                    pararThread = false
                    return
                }
                runOnUiThread{
                    binding.btnExecutar.text = "Executando: $i T: ${currentThread()}"
                    binding.btnExecutar.isEnabled = false

                    if(i == 9){
                        binding.btnExecutar.text = "Executar"
                        binding.btnExecutar.isEnabled = true
                    }

                }

                sleep(1000)
            }
        }
    }
}