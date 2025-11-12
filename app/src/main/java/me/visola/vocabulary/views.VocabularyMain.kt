package me.visola.vocabulary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun VocabularyMain(modifier: Modifier) {
    val languageViewModel: LanguagesViewModel = viewModel()
    val languagesState by languageViewModel.languages
    val selectedLanguage by languageViewModel.selectedLanguage
    val wordsState by languageViewModel.words

    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = modifier.fillMaxWidth()) {
            when {
                languagesState.loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                languagesState.error != null -> Text(text = "An error occurred: ${languagesState.error}")
                else ->
                    Text(
                        text = "${languagesState.languages.size} languages loaded, $selectedLanguage selected"
                    )
            }
        }

        when {
            selectedLanguage == null -> Text("No language selected.")
            wordsState.loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            wordsState.error != null -> Text(text = "Error loading words: ${wordsState.error}")
            else -> {
                LazyColumn(modifier = modifier.fillMaxSize()) {
                    items(wordsState.words) { word ->
                        Text(text = "${word.english} = ${word.original}")
                    }
                }
            }
        }
    }
}