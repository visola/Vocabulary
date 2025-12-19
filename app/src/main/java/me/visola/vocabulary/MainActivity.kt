package me.visola.vocabulary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.visola.vocabulary.ui.theme.VocabularyTheme
import me.visola.vocabulary.views.VocabularyMain
import me.visola.vocabulary.views.WordDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VocabularyTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "vocabularyMain",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("vocabularyMain") {
                            VocabularyMain(navController = navController)
                        }
                        composable("wordDetail/{wordOriginal}") { backStackEntry ->
                            val wordOriginal = backStackEntry.arguments?.getString("wordOriginal")
                            WordDetailScreen(wordOriginal = wordOriginal)
                        }
                    }
                }
            }
        }
    }
}
