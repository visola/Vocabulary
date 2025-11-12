package me.visola.vocabulary

data class LanguagesResponse(
    val languages: List<String>,
)

data class WordsResponse(
    val words: List<Word>,
)

data class Word(
    val english: String,
    val original: String,
    val pronunciation: String,
)
