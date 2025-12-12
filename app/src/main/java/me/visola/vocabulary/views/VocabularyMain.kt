package me.visola.vocabulary.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.visola.vocabulary.LanguagesViewModel

@Composable
fun VocabularyMain(modifier: Modifier) {
    val languageViewModel: LanguagesViewModel = viewModel()
    val languagesState by languageViewModel.languages
    val selectedLanguage by languageViewModel.selectedLanguage
    val wordsState by languageViewModel.words

    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = modifier.fillMaxWidth().padding(16.dp, 32.dp, bottom = 0.dp)) {
            when {
                languagesState.loading -> CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
                languagesState.error != null -> Text(text = "An error occurred: ${languagesState.error}")
                else ->
                    Text(
                        text = "${languagesState.languages.size} languages loaded, $selectedLanguage selected"
                    )
            }
        }

        SearchBar(
            searchTerm = wordsState.searchTerm,
            onSearchTermChanged = { languageViewModel.searchWords(it) },
            modifier = modifier,
        )

        when {
            selectedLanguage == null -> Text("No language selected.")
            wordsState.loading -> CircularProgressIndicator(modifier = modifier.align(Alignment.CenterHorizontally))
            wordsState.error != null -> Text(text = "Error loading words: ${wordsState.error}")
            else -> {
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                ) {
                    items(wordsState.filteredWords) { word ->
                        WordItem(word, modifier = modifier)
                    }
                }
            }
        }
    }
}