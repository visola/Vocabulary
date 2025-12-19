package me.visola.vocabulary

data class LanguagesResponse(
    val languages: List<String>,
)

data class WordsResponse(
    val words: List<Word>,
)

data class Word(
    val type: String,
    val original: String,
    val pronunciation: String,
    val variations: List<Variation>
)

data class Variation(
    val english: String,
    val form: String? = null,
    val gender: String? = null,
    val tense: String? = null,
    val pronunciation: String? = null,
    val examples: List<Example>
)

data class Example(
    val original: String,
    val english: String
)
