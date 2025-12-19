package me.visola.vocabulary.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.visola.vocabulary.LanguagesViewModel

@Composable
fun WordDetailScreen(wordOriginal: String?) {
    val languageViewModel: LanguagesViewModel = viewModel()
    val word = wordOriginal?.let { languageViewModel.getWordByOriginal(it) }

    if (word == null) {
        Text(text = "Word not found")
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Original: ${word.original}")
        Text(text = "Type: ${word.type}")
        Text(text = "Pronunciation: ${word.pronunciation}")

        word.variations.forEach { variation ->
            Text(text = "Variation:")
            Text(text = "  English: ${variation.english}")
            variation.pronunciation?.let { Text(text = "  Pronunciation: $it") }
            variation.tense?.let { Text(text = "  Tense: $it") }
            variation.gender?.let { Text(text = "  Gender: $it") }
            variation.form?.let { Text(text = "  Form: $it") }
        }
    }
}
