package me.visola.vocabulary

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.visola.vocabulary.views.WordItem

data class LanguagesState(
    val loading: Boolean = false,
    val languages: List<String> = emptyList(),
    val error: String? = null,
)

data class WordsState(
    val loading: Boolean = false,
    val words: List<Word> = emptyList(),
    val error: String? = null,
)

class LanguagesViewModel : ViewModel() {

    private val _languages = mutableStateOf(LanguagesState())
    val languages: State<LanguagesState> = _languages

    private val _selectedLanguage = mutableStateOf<String?>(null)
    val selectedLanguage: State<String?> = _selectedLanguage

    private val _words = mutableStateOf(WordsState())
    val words: State<WordsState> = _words

    init {
        fetchLanguages()
    }

    private fun fetchLanguages() {
        _languages.value = _languages.value.copy(
            loading = true
        )

        viewModelScope.launch {
            try {
                val response = languagesService.getLanguages()
                _languages.value = _languages.value.copy(
                    languages = response.languages,
                    loading = false,
                )
                _selectedLanguage.value = response.languages.getOrNull(0)
                fetchWords()
            } catch (e: Exception) {
                _languages.value = _languages.value.copy(
                    error = "Unexpected error loading languages: ${e.message}",
                    loading = false,
                )
            }
        }
    }

    private fun fetchWords() {
        _words.value = _words.value.copy(
            loading = true
        )

        viewModelScope.launch {
            try {
                if (selectedLanguage.value == null) {
                    return@launch
                }

                val response = languagesService.getWords(selectedLanguage.value!!)
                _words.value = _words.value.copy(
                    words = response.words.sortedBy { (english) -> english.lowercase() },
                    loading = false,
                )
            } catch (e: Exception) {
                _words.value = _words.value.copy(
                    error = "Unexpected error loading words: ${e.message}",
                    loading = false,
                )
            }
        }
    }
}