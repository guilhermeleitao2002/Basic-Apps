package com.example.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.helloandroid.ui.theme.HelloAndroidTheme

class DisplayMessageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val message = intent.getStringExtra("EXTRA_MESSAGE") ?: "No message received"

        val words = message.split(" ").filter { it.isNotBlank() }
        val bundle = WordBundle(words)
        val summary = generateSummary(bundle)

        /*val summary = when {
            wordCount == 0 -> "No words found!"
            wordCount == 1 -> "Just one word!"
            else -> "You entered $wordCount words."
        }*/

        setContent {
            HelloAndroidTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = summary, fontSize = 24.sp)
                }
            }
        }
    }
}

fun generateSummary(bundle: WordBundle): String {
    // Extra metric
    val longWords = bundle.words.filter { it.length > 4 }

    return "Words: ${bundle.totalWordCount()}, Avg Length: ${bundle.averageWordLength()}, Long words: ${longWords.joinToString()}, Longest word: ${bundle.longestWord()}"
}
