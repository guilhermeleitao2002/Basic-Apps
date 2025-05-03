package com.example.helloandroid

data class WordBundle(val words: List<String>) {
    private val count = words.size
    private val averageLength = words.map { it.length }.average()
    private val longestWord = words.maxByOrNull { it.length } ?: "N/A"

    fun totalWordCount(): Int {
        return count
    }

    fun averageWordLength(): Double {
        return averageLength
    }

    fun longestWord(): String {
        return longestWord
    }
}
