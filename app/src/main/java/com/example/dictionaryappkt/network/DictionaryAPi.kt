package com.example.dictionaryappkt.network

import com.example.dictionaryappkt.model.Meaning
import com.example.dictionaryappkt.model.WordResponse
import com.example.dictionaryappkt.model.WordResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryAPi {

    @GET("en/{word}")
    suspend fun getMeaning(@Path("word") word:String): Response<List<WordResponseItem>>
}