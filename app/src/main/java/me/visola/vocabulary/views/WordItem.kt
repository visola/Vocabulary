package me.visola.vocabulary.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.visola.vocabulary.Word

@Composable
fun WordItem(word: Word, navController: NavController, modifier: Modifier = Modifier) {
    val variation = word.variations.first()
    Column (
        modifier = modifier
            .wrapContentHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
            ) {
                navController.navigate("wordDetail/${word.original}")
            },
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = variation.english,
            )
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = word.original,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                fontStyle = FontStyle.Italic,
                text = "(${word.type})",
            )
            Text(
                fontStyle = FontStyle.Italic,
                text = variation.pronunciation ?: word.pronunciation,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
        ) {
            val attributes = mutableListOf<String>()
            variation.tense?.let { attributes.add("Tense: $it") }
            variation.gender?.let { attributes.add("Gender: $it") }
            variation.form?.let { attributes.add("Form: $it") }
            Text(
                text = attributes.joinToString(", "),
            )
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}