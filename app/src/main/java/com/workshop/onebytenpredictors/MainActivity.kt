package com.workshop.onebytenpredictors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.workshop.onebytenpredictors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layoutNew.visibility=View.GONE


        binding.buttonPredict.setOnClickListener{
            binding.layoutDefault.visibility= View.GONE
            binding.layoutNew.visibility=View.VISIBLE
        }
    }


}