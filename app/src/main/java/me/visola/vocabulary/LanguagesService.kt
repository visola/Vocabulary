package me.visola.vocabulary

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder()
    .baseUrl("https://raw.githubusercontent.com/visola/Vocabulary/refs/heads/main/languages/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();

val languagesService = retrofit.create(LanguagesService::class.java)

interface LanguagesService {

    @GET("index.json")
    suspend fun getLanguages(): LanguagesResponse

    @GET("{language}.json")
    suspend fun getWords(@Path("language") language: String): WordsResponse

}