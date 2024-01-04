package com.example.dictionaryappkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.dictionaryappkt.databinding.ActivityMainBinding
import com.example.dictionaryappkt.model.WordResponse
import com.example.dictionaryappkt.model.WordResponseItem
import com.example.dictionaryappkt.network.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchBtn.setOnClickListener {
            val word = binding.searchEt.text.toString()
            getMeaning(word)
        }
    }

    private fun getMeaning(word: String) {
        setInProgress(true)
        GlobalScope.launch {
            val response = RetrofitInstance.dictionaryAPi.getMeaning(word)
            Log.e("Tag",response.body().toString())

            runOnUiThread{
                setInProgress(false)
                response.body()?.first()?.let{
                    setUi(it)
                }
            }
        }
    }
    private fun setUi(response: WordResponseItem)
    {
        binding.word.text = response.word.toString()
        binding.phonetic.text = response.phonetic.toString()
    }

    private fun setInProgress(inProgress: Boolean) {
        if(inProgress)
        {
            binding.searchBtn.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
        }
        else{
            binding.searchBtn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}