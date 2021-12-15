package de.jannikpaul.timefighter

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HighscoreActivity : AppCompatActivity() {

    private lateinit var listView : ListView

    val highscoreEntryList = mutableListOf<String>()

    var countDownTimer = object : CountDownTimer(10000, 1000){
        override fun onTick(p0: Long) {
            var zahl2 = p0 / 1000
            itemHinzufuegen(zahl2)
        }

        override fun onFinish() {

        } //leer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        listView = findViewById<ListView>(R.id.highscoreListe)

        val adapter = HighscoreAdapter(this, highscoreEntryList)
        listView.adapter = adapter

        countDownTimer.start()
    }

    fun itemHinzufuegen(zahl: Long) {
        var zahl = zahl + 1
        var text = "$zahl"
        highscoreEntryList.add(text)
        listView.adapter = HighscoreAdapter(this, highscoreEntryList)
    }

    companion object {
        val HIGHSCORES = listOf<HighscoreEntry>(
            HighscoreEntry(10, "Hubert"),
            HighscoreEntry(8, "Asmodina07"),
            HighscoreEntry(9, "Anonym"),
        )
    }

}