package com.example

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

/**
 * Helper class for managing TextToSpeech synthesis for English verbs and sentences.
 */
class TextToSpeechManager(context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isReady: Boolean = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w("TTSManager", "Language US is not available or missing data, trying default")
                tts?.setLanguage(Locale.ENGLISH)
            }
            tts?.setSpeechRate(0.92f) // slightly natural, clear educational pace
            isReady = true
        } else {
            Log.e("TTSManager", "TTS initialization failed with status $status")
        }
    }

    fun speak(text: String) {
        if (text.isBlank()) return
        try {
            tts?.stop()
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "phrasal_tts_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            Log.e("TTSManager", "Error in speak", e)
        }
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (e: Exception) {
            Log.e("TTSManager", "Error shutting down TTS", e)
        }
        tts = null
        isReady = false
    }
}

/**
 * Composable utility to remember and automatically lifecycle-manage TextToSpeechManager.
 */
@Composable
fun rememberTextToSpeechManager(): TextToSpeechManager {
    val context = LocalContext.current
    val manager = remember { TextToSpeechManager(context) }
    DisposableEffect(Unit) {
        onDispose {
            manager.shutdown()
        }
    }
    return manager
}
