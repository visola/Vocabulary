package me.visola.vocabulary.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    searchTerm: String,
    onSearchTermChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchTerm,
        onValueChange = onSearchTermChanged,
        label = { Text("Search") },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}
