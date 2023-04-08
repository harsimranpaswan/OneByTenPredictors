package com.workshop.onebytenpredictors

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.onebytenpredictors.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val api="7cb2b436829844d884370c59671ee9ca"
    val country="in"
    lateinit var modelClassArrayList:ArrayList<ModelClass>
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter:Adapter


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutNew.visibility=View.GONE
        binding.buttonPredict.isEnabled=false


        binding.etText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!binding.etText.text.isNullOrEmpty()) {
                    binding.buttonPredict.isEnabled=true
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.buttonPredict.setOnClickListener {
            binding.layoutDefault.visibility = View.GONE

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = Adapter(this@MainActivity, modelClassArrayList)
//            binding.recyclerDefault.adapter = adapter
//            binding.recyclerDefault.layoutManager = layoutManager

//            findNews()

            binding.textV.setText(binding.etText.text)
            binding.layoutNew.visibility = View.VISIBLE
        }
    }

//    private fun findNews(){
//        ApiUtilities.getApiInterface().getNews(country,100,api).enqueue(new Callback<NewsArrayClass>() {
//            @Override
//            public fun onResponse(Call<NewsArrayClass> call, Response<NewsArrayClass> response){
//
//            }
//        });
//    }


}