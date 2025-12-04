package me.visola.vocabulary.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.visola.vocabulary.Word

@Composable
fun WordItem(word: Word, modifier: Modifier) {
    Column (
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
                .padding(16.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = word.english,
            )
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = word.original,
            )
        }
        Text(
            modifier = modifier.fillMaxWidth()
                .padding(16.dp, 8.dp),
            text = "Pronunciation: ${word.pronunciation}",
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}