package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // The current _word. Encapsulation for LiveData object.
    private val _word = MutableLiveData<String>() // Private version
    val word: LiveData<String> // Made it public
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // The list of words - the front of the list is the next _word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val evenGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()
        Log.i("GameViewModel", "GameViewModel is created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next _word in the list
     */
    private fun nextWord() {
        when {
            wordList.isNotEmpty() -> //Select and remove a _word from the list
                _word.value = wordList.removeAt(0)
            else -> onGameFinsh()
        }
    }

    fun onSkip() {
        if (!wordList.isEmpty()) {
            _score.value = score.value?.minus(1)
        }
        nextWord()
    }

    fun onCorrect() {
        if (!wordList.isEmpty()) {
            _score.value = score.value?.plus(1)
        }
        nextWord()
    }

    fun onGameFinsh() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}